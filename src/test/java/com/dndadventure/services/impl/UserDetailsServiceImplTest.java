package com.dndadventure.services.impl;

import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.testutils.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserRepository userRepositoryMock;
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() {
        this.userRepositoryMock = mock(UserRepository.class);
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepositoryMock);
    }

    @Test
    public void whenLoadUserByUsernameValidThenReturnUser() {
        User user = TestUtils.mockUser();
        UserPrincipal userPrincipalMock = mockUserPrincipal(user);
        when(this.userRepositoryMock.findByUsername(userPrincipalMock.getUsername()))
            .thenReturn(Optional.of(user));

        UserDetails result = this.userDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    public void whenLoadUserByUsernameAndUsernameDoesNotExistThenThrowException() {
        when(this.userRepositoryMock.findByUsername(anyString()))
            .thenReturn(Optional.empty());

        this.expectedException.expect(UsernameNotFoundException.class);
        this.expectedException.expectMessage("User not found");

        this.userDetailsService.loadUserByUsername(anyString());
    }

    private UserPrincipal mockUserPrincipal(User user) {
        return new UserPrincipal(user);
    }


}
