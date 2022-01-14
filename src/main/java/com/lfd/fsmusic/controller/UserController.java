package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.LoginDto;
import com.lfd.fsmusic.service.dto.in.UserCreateDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(value = "查找用户")
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query")
    })
    ApiResponse list(
            @PageableDefault(sort = {
                    "createTime" }, size = 2, direction = Sort.Direction.ASC) @ApiIgnore Pageable pageable) {
        return ApiResponse.ok(uService.find(pageable).map(uMapper::toVo));
    }

    @ApiOperation(value = "用户创建")
    @PostMapping
    ApiResponse create(@Validated @RequestBody UserCreateDto user) {
        UserDto uDo = uService.create(user);
        UserVo uVo = uMapper.toVo(uDo);
        return ApiResponse.ok(uVo);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    ApiResponse get(@PathVariable String id) {
        return ApiResponse.ok(uMapper.toVo(uService.findById(id)));
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/{id}")
    ApiResponse update(@PathVariable String id, @Validated @RequestBody UserCreateDto uCreateDto) {
        return ApiResponse.ok(uMapper.toVo(uService.update(id, uCreateDto)));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    ApiResponse delete(@PathVariable String id) {
        uService.delete(id);
        return ApiResponse.ok();
    }

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody LoginDto user) {
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
