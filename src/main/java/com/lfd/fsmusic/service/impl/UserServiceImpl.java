package com.lfd.fsmusic.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.repository.UserRepository;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.UserCreateDto;
import com.lfd.fsmusic.service.dto.UserDto;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository uRepo;
    private final PasswordEncoder pEncoder;

    public UserServiceImpl(UserRepository uRepo, UserMapper uMapper, PasswordEncoder pEncoder) {
        this.uRepo = uRepo;
        this.uMapper = uMapper;
        this.pEncoder = pEncoder;
    }

    private final UserMapper uMapper;

    @Override
    public List<UserDto> list() {
        return uRepo.findAll().stream()
                .map(uMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateDto user) {
        if (uRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new BizException(EType.USERNAME_DUPLICATE);
        }
        User eUser = uMapper.toEntity(user);
        eUser.setEnabled(true);
        eUser.setLocked(false);
        eUser.setPassword(pEncoder.encode(user.getPassword()));
        return uMapper.toDto(uRepo.save(eUser));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = uRepo.findByUsername(username);
        if (!oUser.isPresent()) {
            throw new BizException(EType.USERNAME_NOT_FOUND);
        }
        return oUser.get();
    }
}
