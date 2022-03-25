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
import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginReq;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class UserServiceImpl extends BaseService implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository uRepo;
    private final PasswordEncoder pEncoder;
    private final UserMapper uMapper;

    @Override
    public List<UserDto> list() {
        return uRepo.findAll().stream()
                .map(uMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserCreateReq user) {
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
    public UserDto update(String id, UserCreateReq uDto) {
        findByIdAndCheck(id);
        User user = uRepo.save(uMapper.updateEntity(id, uDto));
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
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = uRepo.findByUsername(username);
        if (!oUser.isPresent()) {
            throw new BizException(EType.USERNAME_NOT_FOUND);
        }
        return oUser.get();
    }

    @Override
    public String createToken(LoginReq loginDto) {
        User user = loadUserByUsername(loginDto.getUsername());
        if (!pEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new BizException(EType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(EType.USER_DISABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(EType.USER_LOCKED);
        }

        Date exp = new Date(System.currentTimeMillis() + SecurityCfg.EXPIRE_TIME);
        logger.debug("[uDto] jwt exp =>" + exp.getTime());
        String token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC512(SecurityCfg.SECRET));
        return token;
    }

    @Override
    public UserDto current() {
        User user = getCurrentUser();
        return uMapper.toDto(user);
    }
}
