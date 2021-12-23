package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.repository.dto.UserDto;
import com.lfd.fsmusic.repository.entity.User;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * UserMapper
 */
@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    UserVo toVo(UserDto user);    
    
}