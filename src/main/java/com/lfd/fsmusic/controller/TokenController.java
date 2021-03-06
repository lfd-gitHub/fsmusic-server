package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.in.LoginReq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Token接口")
@RequestMapping("/api/token")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class TokenController {

    private final UserService userService;

    @PostMapping
    public ApiResponse create(@RequestBody LoginReq loginDto) {
        return ApiResponse.ok(userService.createToken(loginDto));
    }
}
