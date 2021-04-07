package com.dndadventure.services.impl;

import com.dndadventure.domain.entities.RefreshToken;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.RefreshTokenRepository;
import com.dndadventure.services.AuthService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {
    private static final String ACCESS_TOKEN_NAME = "access_token";
    private static final String REFRESH_TOKEN_NAME = "refresh_token";
    private static final String TOKEN_VALUE = "Default token value";
    private static final int ACCESS_TOKEN_MAX_AGE = 15 * 60;
    private static final String DEFAULT_ID = "aaa-aaa-aaa";
    private static final int REFRESH_TOKEN_MAX_AGE = 61;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RefreshTokenRepository refreshTokenRepositoryMock;
    private AuthService authService;

    @Before
    public void setUp() {
        this.refreshTokenRepositoryMock = mock(RefreshTokenRepository.class);
        this.authService = new AuthServiceImpl(this.refreshTokenRepositoryMock);
    }

    @Test
    public void givenValidUserWhenCreateAccessTokenCookieThenReturnCookieWithThisUser() {
        ReflectionTestUtils.setField(authService, "SECRET", "secret");
        ReflectionTestUtils.setField(authService, "IS_SECURE", false);
        ReflectionTestUtils.setField(authService, "HTTP_ONLY", true);
        Cookie cookie = authService.createAccessTokenCookie(createStubUserPrincipal());
        assertEquals(ACCESS_TOKEN_NAME, cookie.getName());
        assertEquals("/", cookie.getPath());
        assertEquals(ACCESS_TOKEN_MAX_AGE, cookie.getMaxAge());
        assertFalse(cookie.getSecure());
        assertTrue(cookie.isHttpOnly());
    }

    @Test
    public void givenValidUserWhenCreateRefreshTokenCookieThenReturnCookie() {
        ReflectionTestUtils.setField(authService, "SECRET", "secret");
        ReflectionTestUtils.setField(authService, "IS_SECURE", false);
        ReflectionTestUtils.setField(authService, "HTTP_ONLY", true);
        RefreshToken refreshToken = this.createStubRefreshToken();
        refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_MAX_AGE));

        when(this.refreshTokenRepositoryMock.findAllByUser(any(User.class)))
            .thenReturn(Collections.singletonList(refreshToken));
        doNothing().when(this.refreshTokenRepositoryMock).deleteById(anyString());
        when(this.refreshTokenRepositoryMock.save(any(RefreshToken.class)))
            .thenReturn(refreshToken);

        UserPrincipal stubUserPrincipal = createStubUserPrincipal();
        Cookie refreshTokenCookie = this.authService.createRefreshTokenCookie(stubUserPrincipal);

        assertEquals(REFRESH_TOKEN_NAME, refreshTokenCookie.getName());
        verify(this.refreshTokenRepositoryMock).findAllByUser(stubUserPrincipal.getUser());
        verify(this.refreshTokenRepositoryMock).deleteById(DEFAULT_ID);
        verify(this.refreshTokenRepositoryMock).save(any(RefreshToken.class));
    }

    @Test
    public void givenNoCookiesInRequestWhenGetAccessTokenCookieThenReturnEmptyOptional() {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(null);

        Optional<Cookie> optionalCookie = this.authService.getAccessTokenCookie(requestMock);

        assertFalse(optionalCookie.isPresent());
        verify(requestMock).getCookies();
    }

    @Test
    public void givenNoAccessTokenCookiesInRequestWhenGetAccessTokenCookieThenReturnEmptyOptional() {
        Cookie[] cookies = {new Cookie(REFRESH_TOKEN_NAME, TOKEN_VALUE)};
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(cookies);

        Optional<Cookie> optionalCookie = this.authService.getAccessTokenCookie(requestMock);

        assertFalse(optionalCookie.isPresent());
        verify(requestMock, times(2)).getCookies();
    }

    @Test
    public void givenAccessTokenCookieInRequestWhenGetAccessTokenCookieThenReturnOptionalOfCookie() {
        Cookie[] cookies = {new Cookie(ACCESS_TOKEN_NAME, TOKEN_VALUE)};

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(cookies);

        Optional<Cookie> optionalCookie = this.authService.getAccessTokenCookie(requestMock);

        assertTrue(optionalCookie.isPresent());
        assertEquals(ACCESS_TOKEN_NAME, optionalCookie.get().getName());
        assertEquals(TOKEN_VALUE, optionalCookie.get().getValue());
        verify(requestMock, times(2)).getCookies();
    }

    @Test
    public void givenNoCookiesInRequestWhenGetRefreshTokenCookieThenReturnEmptyOptional() {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(null);

        Optional<Cookie> optionalCookie = this.authService.getRefreshTokenCookie(requestMock);

        assertFalse(optionalCookie.isPresent());
        verify(requestMock).getCookies();
    }

    @Test
    public void givenNoRefreshTokenCookiesInRequestWhenGetRefreshTokenCookieThenReturnEmptyOptional() {
        Cookie[] cookies = {new Cookie(ACCESS_TOKEN_NAME, TOKEN_VALUE)};
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(cookies);

        Optional<Cookie> optionalCookie = this.authService.getRefreshTokenCookie(requestMock);

        assertFalse(optionalCookie.isPresent());
        verify(requestMock, times(2)).getCookies();
    }

    @Test
    public void givenRefreshTokenCookieInRequestWhenGetRefreshTokenCookieThenReturnOptionalOfCookie() {
        Cookie[] cookies = {new Cookie(REFRESH_TOKEN_NAME, TOKEN_VALUE)};

        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        when(requestMock.getCookies()).thenReturn(cookies);

        Optional<Cookie> optionalCookie = this.authService.getRefreshTokenCookie(requestMock);

        assertTrue(optionalCookie.isPresent());
        assertEquals(REFRESH_TOKEN_NAME, optionalCookie.get().getName());
        assertEquals(TOKEN_VALUE, optionalCookie.get().getValue());
        verify(requestMock, times(2)).getCookies();
    }

    @Test
    public void givenCookieWhenGetSubjectByAccessTokenCookieThenReturnCookieSubClaim() {
        ReflectionTestUtils.setField(authService, "SECRET", "secret");
        Cookie cookie = authService.createAccessTokenCookie(createStubUserPrincipal());
        String subject = authService.getSubjectByAccessTokenCookie(cookie);
        assertNotNull(subject);
    }

    //-----------------------

    @Test
    public void givenValueNotMatchingAnyRefreshTokenWhenFindByValueIsInvokedThenNotFoundExceptionIsThrown() {
        final String givenValue = "It is invalid refresh token value.";

        when(this.refreshTokenRepositoryMock.findFirstByValue(anyString())).thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Refresh token not found.");

        this.authService.findByValue(givenValue);
    }

    @Test
    public void givenValueNotMatchingExpiredRefreshTokenWhenFindByValueIsInvokedThenNotFoundExceptionIsThrown() {
        RefreshToken expiredRefreshToken = this.createStubRefreshToken();
        expiredRefreshToken.setExpiresAt(LocalDateTime.now().minusMinutes(REFRESH_TOKEN_MAX_AGE));

        when(this.refreshTokenRepositoryMock.findFirstByValue(TOKEN_VALUE))
            .thenReturn(Optional.of(expiredRefreshToken));
        doNothing().when(this.refreshTokenRepositoryMock).deleteById(expiredRefreshToken.getUuid());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Expired refresh token.");

        this.authService.findByValue(TOKEN_VALUE);
    }

    @Test
    public void givenValueMatchingRefreshTokenWhenFindByValueIsInvokedThenReturnThisRefreshToken() {
        RefreshToken refreshToken = this.createStubRefreshToken();
        refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_MAX_AGE));

        when(this.refreshTokenRepositoryMock.findFirstByValue(anyString())).thenReturn(Optional.of(refreshToken));

        assertEquals(refreshToken, this.authService.findByValue(TOKEN_VALUE));

        verify(this.refreshTokenRepositoryMock).findFirstByValue(TOKEN_VALUE);
        verify(this.refreshTokenRepositoryMock, times(0)).deleteById(anyString());
    }

    @Test
    public void givenValidRefreshTokenWhenObtainAccessTokenIsInvokedThenReturnCookiesWithTokens(){
        ReflectionTestUtils.setField(authService, "SECRET", "secret");
        RefreshToken refreshToken = createStubRefreshToken();
        refreshToken.setExpiresAt(LocalDateTime.now().plusMinutes(REFRESH_TOKEN_MAX_AGE));
        when(this.refreshTokenRepositoryMock.findAllByUser(any(User.class)))
            .thenReturn(Collections.singletonList(refreshToken));
        doNothing().when(this.refreshTokenRepositoryMock).deleteById(anyString());
        when(this.refreshTokenRepositoryMock.save(any(RefreshToken.class))).thenReturn(refreshToken);

        Iterable<Cookie> cookies = authService.obtainNewTokens(refreshToken);
        Iterator<Cookie> iterator = cookies.iterator();
        assertEquals(iterator.next().getName(), ACCESS_TOKEN_NAME);
        assertEquals(iterator.next().getName(), REFRESH_TOKEN_NAME);
    }

    private RefreshToken createStubRefreshToken() {
        return (RefreshToken) new RefreshToken()
            .setValue(TOKEN_VALUE)
            .setUser(this.createStubUser())
            .setUuid(DEFAULT_ID);
    }

    private UserPrincipal createStubUserPrincipal() {
        return new UserPrincipal(this.createStubUser());
    }

    private User createStubUser() {
        return (User) new User()
            .setUsername("username")
            .setPassword("password")
            .setUuid(DEFAULT_ID);
    }
}
