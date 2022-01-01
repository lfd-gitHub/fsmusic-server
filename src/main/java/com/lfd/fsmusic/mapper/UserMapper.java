package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.dto.UserCreateDto;
import com.lfd.fsmusic.service.dto.UserDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

/**
 * UserMapper
 */
@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mappings({ @Mapping(target = "password", ignore = true) })
    UserVo toVo(UserDto user);

    User toEntity(UserCreateDto createUser);
}