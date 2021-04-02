package com.dndadventure.config;

import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;
import com.dndadventure.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final boolean isProd;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   boolean isProd) {
        this.authenticationManager = authenticationManager;
        this.isProd = isProd;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                Collections.emptyList()
            );
            return this.authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        WebApplicationContext webApplicationContext =
            WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());

        AuthService authService = webApplicationContext.getBean(AuthService.class);
        UserPrincipal user = (UserPrincipal) authResult.getPrincipal();
        Cookie accessTokenCookie = authService.createAccessTokenCookie(user);
        Cookie refreshTokenCookie = authService.createRefreshTokenCookie(user);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        if (isProd) {
            addSameSiteCookieAttribute(response);
        }
    }

    private void addSameSiteCookieAttribute(HttpServletResponse response) {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) {
            System.out.println(header);
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE,
                    String.format("%s; %s", header, "SameSite=None"));
                firstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE,
                String.format("%s; %s", header, "SameSite=None"));
        }
        System.out.println(headers);
    }
}
