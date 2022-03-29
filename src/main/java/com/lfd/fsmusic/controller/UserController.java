package com.lfd.fsmusic.controller;

import javax.annotation.security.RolesAllowed;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.UserDto;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class UserController {

    private final UserService uService;
    private final UserMapper uMapper;

    @ApiOperation(value = "查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "size", dataType = "Integer", dataTypeClass = Integer.class, paramType = "query"),
            @ApiImplicitParam(name = "sort", dataType = "String", dataTypeClass = String.class, paramType = "query"),
    })
    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    ApiResponse list(
            @PageableDefault(sort = {
                    "createTime" }, size = 2, direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable) {
        return ApiResponse.ok(uService.list(pageable).map(uMapper::toVo));
    }

    @ApiOperation(value = "用户创建")
    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    ApiResponse create(@Validated @RequestBody UserCreateReq req) {
        UserDto uDo = uService.create(uMapper.fromReq(req));
        UserVo uVo = uMapper.toVo(uDo);
        return ApiResponse.ok(uVo);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    ApiResponse get(@PathVariable String id) {
        return ApiResponse.ok(uMapper.toVo(uService.get(id)));
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/me")
    ApiResponse me() {
        return ApiResponse.ok(uMapper.toVo(uService.current()));
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping("/{id}")
    ApiResponse update(@PathVariable String id, @Validated @RequestBody UserCreateReq req) {
        return ApiResponse.ok(uMapper.toVo(uService.update(id, uMapper.fromReq(req))));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    ApiResponse delete(@PathVariable String id) {
        uService.delete(id);
        return ApiResponse.ok();
    }

    @ApiOperation("登出接口")
    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException(
                "This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
