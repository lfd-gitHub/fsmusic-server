package com.lfd.fsmusic.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.lfd.fsmusic.controller.vo.UserVo;
import com.lfd.fsmusic.mapper.UserMapper;
import com.lfd.fsmusic.service.base.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService uService;
    private final UserMapper uMapper;

    public UserController(UserService uService,UserMapper uMapper) {
        this.uService = uService;
        this.uMapper = uMapper;
    }

    @GetMapping("/list")
    List<UserVo> list(){
        return uService.list().stream().map(uMapper::toVo).collect(Collectors.toList());
    }


}
