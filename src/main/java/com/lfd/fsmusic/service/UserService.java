package com.lfd.fsmusic.service;

import java.util.List;

import com.lfd.fsmusic.service.dto.UserCreateDto;
import com.lfd.fsmusic.service.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    List<UserDto> list();

    UserDto create(UserCreateDto user);
}
