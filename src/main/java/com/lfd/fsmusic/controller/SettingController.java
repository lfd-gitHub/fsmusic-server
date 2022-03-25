package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.SettingMapper;
import com.lfd.fsmusic.service.SettingService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "设置接口")
@RequestMapping("/api/setting")
@RestController
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class SettingController {

    private final SettingMapper settingMapper;
    private final SettingService settingService;

    @GetMapping("/site")
    public ApiResponse site() {
        return ApiResponse.ok(settingMapper.toVo(settingService.getSiteSettings()));
    }

}
