package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.RoleVo;
import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.repository.entity.Role;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * UserMapper
 */
@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    RoleVo toRoleVo(Role role);

    UserVo toVo(UserDto user);

    User toEntity(UserCreateReq createUser);

    User updateEntity(String id, UserCreateReq createDto);
}