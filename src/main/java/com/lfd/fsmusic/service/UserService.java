package com.lfd.fsmusic.service;

import java.util.List;

import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginDto;
import com.lfd.fsmusic.service.dto.in.UserCreateDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Page<UserDto> find(Pageable pageable);

    List<UserDto> list();

    UserDto findById(String id);

    UserDto update(String id, UserCreateDto uDto);

    boolean delete(String id);

    UserDto create(UserCreateDto user);

    String createToken(LoginDto loginDto);

    UserDto current();
}
