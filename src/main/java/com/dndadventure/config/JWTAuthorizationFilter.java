package com.dndadventure.config;

import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.services.AuthService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    private AuthService authService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        WebApplicationContext webApplicationContext =
            WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        this.userRepository = webApplicationContext.getBean(UserRepository.class);
        this.authService = webApplicationContext.getBean(AuthService.class);

        Optional<Cookie> optionalCookie = this.authService.getAccessTokenCookie(request);

        if (optionalCookie.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = this.getAuthentication(optionalCookie.get());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            chain.doFilter(request,response);
        } catch (ParseException e ){

        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(Cookie accessTokenCookie) throws ParseException {
        if (accessTokenCookie == null) {
            return null;
        }

        String username = this.authService.getSubjectByAccessTokenCookie(accessTokenCookie);

        if (username != null) {
            Optional<User> user = this.userRepository.findByUsername(username);
            if (user.isEmpty()) {
                return null;
            }
            List<SimpleGrantedAuthority> authorities = user.get()
                .getUserRoles()
                .stream()
                .map(UserRole::getRole)
                .map(Enum::name)
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(user.get(), null, authorities);
        }
        return null;
    }
}
