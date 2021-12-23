package com.lfd.fsmusic.service.base;

import java.util.List;

import com.lfd.fsmusic.repository.dto.UserDto;

public interface UserService {
    List<UserDto> list();
}
