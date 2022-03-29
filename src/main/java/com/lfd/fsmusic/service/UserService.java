package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginReq;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, BaseService<UserDto> {

    String createToken(LoginReq loginDto);

    UserDto current();
}
