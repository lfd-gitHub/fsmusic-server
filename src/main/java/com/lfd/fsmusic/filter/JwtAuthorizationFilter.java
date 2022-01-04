package com.lfd.fsmusic.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lfd.fsmusic.config.SecurityCfg;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader(SecurityCfg.HEADER_KEY);
        if (token == null || !token.startsWith(SecurityCfg.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken aToken = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(aToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        token = token.replace(SecurityCfg.TOKEN_PREFIX, "");
        String username = JWT.require(Algorithm.HMAC512(SecurityCfg.SECRET))
                .build().verify(token)
                .getSubject();
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, token, new ArrayList<>());
        }
        return null;
    }

}
