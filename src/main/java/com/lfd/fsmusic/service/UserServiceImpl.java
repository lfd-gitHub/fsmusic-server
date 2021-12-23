package com.lfd.fsmusic.service;

import java.util.List;
import java.util.stream.Collectors;

import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.repository.UserRepository;
import com.lfd.fsmusic.service.base.UserService;
import com.lfd.fsmusic.service.dto.UserDto;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository uRepo;

    public UserServiceImpl(UserRepository uRepo, UserMapper uMapper) {
        this.uRepo = uRepo;
        this.uMapper = uMapper;
    }

    private final UserMapper uMapper;

    @Override
    public List<UserDto> list() {
        return uRepo.findAll().stream().map(uMapper::toDto).collect(Collectors.toList());
    }
    
}
