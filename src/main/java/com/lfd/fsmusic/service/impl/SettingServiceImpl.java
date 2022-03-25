package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.dto.SettingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class SettingServiceImpl implements SettingService {

    private final SettingDto settingDto;

    @Override
    public SettingDto getSiteSettings() {
       return settingDto;
    }

    @Override
    public File.Storage getDefaultStorage() {
        return File.Storage.COS;
    }
}
