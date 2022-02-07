package com.lfd.fsmusic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lfd.fsmusic.config.SecurityCfg;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.repository.UserRepository;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginDto;
import com.lfd.fsmusic.service.dto.in.UserCreateDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        if (eUser.getNickname() == null)
            eUser.setNickname(user.getUsername());
        eUser.setEnabled(true);
        eUser.setLocked(false);
        eUser.setPassword(pEncoder.encode(user.getPassword()));
        return uMapper.toDto(uRepo.save(eUser));
    }

    public User findByIdAndCheck(String id) {
        Optional<User> user = uRepo.findById(id);
        if (!user.isPresent())
            throw new BizException(EType.USERNAME_NOT_FOUND);
        return user.get();
    }

    @Override
    public UserDto findById(String id) {
        return uMapper.toDto(findByIdAndCheck(id));
    }

    @Override
    public UserDto update(String id, UserCreateDto uDto) {
        findByIdAndCheck(id);
        User user = uRepo.save(uMapper.toEntity(uDto));
        return uMapper.toDto(user);
    }

    @Override
    public boolean delete(String id) {
        uRepo.deleteById(id);
        return true;
    }

    @Override
    public Page<UserDto> find(Pageable pageable) {
        return uRepo.findAll(pageable).map(uMapper::toDto);
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = uRepo.findByUsername(username);
        if (!oUser.isPresent()) {
            throw new BizException(EType.USERNAME_NOT_FOUND);
        }
        UserDto uDto = uMapper.toDto(oUser.get());
        logger.debug("uDto = " + uDto);
        return uDto;
    }

    @Override
    public String createToken(LoginDto loginDto) {
        UserDto user = loadUserByUsername(loginDto.getUsername());
        if (!pEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BizException(EType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(EType.USER_DISABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(EType.USER_LOCKED);
        }

        String token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityCfg.EXPIRE_TIME))
                .sign(Algorithm.HMAC512(SecurityCfg.SECRET));
        return token;
    }

    @Override
    public UserDto current() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return loadUserByUsername(auth.getName());
    }
}
