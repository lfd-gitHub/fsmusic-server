package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.RoleVo;
import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.mapper.base.IVoMapper;
import com.lfd.fsmusic.repository.entity.Role;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * UserMapper
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends IVoMapper<UserVo, UserDto>, IEntityMapper<User, UserDto> {

    RoleVo toRoleVo(Role role);

    UserDto fromReq(UserCreateReq createUser);
}