package com.example.demo.infrastructure.security;

import com.example.demo.api.exceptions.InvalidJwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Filter is applied to each API (/api/**) with exception of the refresh token endpoint (/api/auth/token) and login endpoint (/api/auth/signin).
 * Check for access token in Authorization header.
 * If Access token is found in the header, delegate authentication to JwtAuthenticationProvider
 * otherwise throw authentication exception
 * Invokes success or failure strategies based on the outcome of authentication process performed by JwtAuthenticationProvider
 */
public class JwtTokenFilter extends OncePerRequestFilter {
    List<String> SKIP_URLS = Arrays.asList(SecurityConfig.PUBLIC_URLS);

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (SKIP_URLS.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtTokenProvider.resolveToken(request);

        try {
            if (jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
        } catch (InvalidJwtAuthenticationException ex) {
            HashMap<String, String> result = new HashMap();
            result.put("error", ex.getMessage());
            response.setHeader("Content-Type", "application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(jsonBytes(result));
        }
    }

    private byte[] jsonBytes(HashMap<String, String> result) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(result);
        return serialized.getBytes();
    }
}
