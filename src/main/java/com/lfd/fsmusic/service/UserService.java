package com.lfd.fsmusic.service;

import java.util.List;

import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginReq;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Page<UserDto> find(Pageable pageable);

    List<UserDto> list();

    UserDto findById(String id);

    UserDto update(String id, UserCreateReq uDto);

    boolean delete(String id);

    UserDto create(UserCreateReq user);

    String createToken(LoginReq loginDto);

    UserDto current();
}
