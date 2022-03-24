package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.mapper.SettingMapper;
import com.lfd.fsmusic.service.SettingService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "设置接口")
@RequestMapping("/api/setting")
@RestController
public class SettingController {

    private SettingMapper settingMapper;
    private SettingService settingService;

    public SettingController(SettingMapper settingMapper,SettingService settingService) {
        this.settingMapper = settingMapper;
        this.settingService = settingService;
    }

    @GetMapping("/site")
    public ApiResponse site(){
        return ApiResponse.ok(settingMapper.toVo(settingService.getSiteSettings()));
    }

}
