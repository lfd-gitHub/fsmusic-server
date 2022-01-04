package com.lfd.fsmusic.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.UserCreateDto;
import com.lfd.fsmusic.service.dto.UserDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户接口")
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService uService;
    private final UserMapper uMapper;

    public UserController(UserService uService, UserMapper uMapper) {
        this.uService = uService;
        this.uMapper = uMapper;
    }

    @ApiOperation(value = "用户列表")
    @GetMapping
    ApiResponse list() {
        List<UserVo> voList = uService.list().stream()
                .map(uMapper::toVo)
                .collect(Collectors.toList());
        return ApiResponse.ok(voList);
    }

    @ApiOperation(value = "用户创建")
    @PostMapping
    ApiResponse create(@RequestBody UserCreateDto user) {
        UserDto uDo = uService.create(user);
        UserVo uVo = uMapper.toVo(uDo);
        return ApiResponse.ok(uVo);
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody UserCreateDto user) {
        throw new IllegalStateException(
                "This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @ApiOperation("登出接口")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException(
                "This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
