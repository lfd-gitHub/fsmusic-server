package com.lfd.fsmusic.config;

import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.repository.entity.WithMockCustomUser;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WithMockCustomUserSecurityContextFactory
        implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        log.info("[Test] - createSecurityContext ========================== ");
        User principal = (User) SpringUtil.getBean(UserDetailsService.class).loadUserByUsername(customUser.username());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(),
                principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}