package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.service.UserService;
import com.lfd.fsmusic.service.dto.in.LoginDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "Token接口")
@RequestMapping("/api/token")
@RestController
public class TokenController {

    UserService userService;

    @PostMapping
    public ApiResponse create(@RequestBody LoginDto loginDto) {
        return ApiResponse.ok(userService.createToken(loginDto));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
