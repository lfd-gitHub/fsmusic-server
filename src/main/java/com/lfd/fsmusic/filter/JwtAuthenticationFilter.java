package com.lfd.fsmusic.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.config.SecurityCfg;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cn.hutool.json.JSONUtil;

/**
 * JwtFilter
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            LoginDto user = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
            logger.error("[attemptAuthentication] = {}", e.getMessage());
            throw new BizException(EType.UNAUTHORIZED);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserDto u = (UserDto) authResult.getPrincipal();
        String token = JWT.create().withSubject(u.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityCfg.EXPIRE_TIME))
                .sign(Algorithm.HMAC512(SecurityCfg.SECRET));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.addHeader(SecurityCfg.HEADER_KEY, SecurityCfg.TOKEN_PREFIX + token);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        String resp = JSONUtil.parse(ApiResponse.ok(data)).toString();
        response.getWriter().write(resp);
        response.getWriter().flush();
    }
}