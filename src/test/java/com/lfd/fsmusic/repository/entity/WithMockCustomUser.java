package com.lfd.fsmusic.repository.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.lfd.fsmusic.config.WithMockCustomUserSecurityContextFactory;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    String username() default "lfd";
}
