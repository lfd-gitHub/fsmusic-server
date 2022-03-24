package com.lfd.fsmusic.service;


import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.dto.SettingDto;

public interface SettingService {

    SettingDto getSiteSettings();
    File.Storage getDefaultStorage();

}
