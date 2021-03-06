package com.lfd.fsmusic.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lfd.fsmusic.config.SecurityCfg;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.repository.UserRepository;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.base.SimpleBaseServiceImpl;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class UserServiceImpl extends SimpleBaseServiceImpl<User, UserDto> implements UserService {

    private final UserRepository uRepo;
    private final PasswordEncoder pEncoder;
    private final UserMapper uMapper;

    @Override
    protected UserRepository getRepo() {
        return uRepo;
    }

    @Override
    protected IEntityMapper<User, UserDto> getMapper() {
        return uMapper;
    }

    @Override
    public UserDto create(UserDto user) {
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
        log.debug("[uDto] jwt exp =>" + exp.getTime());
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
