package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.dto.SettingDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {

    @Value("${cos.bucket}")
    private String bucket;
    @Value("${cos.region}")
    private String region;
    @Value("${cos.secret-id}")
    private String secretId;
    @Value("${cos.secret-key}")
    private String secretKey;


    @Override
    public SettingDto getSiteSettings() {
        SettingDto dto = new SettingDto();
        dto.setStorage(getDefaultStorage());
        dto.setOsBucket(bucket);
        dto.setOsRegion(region);
        dto.setOsSecretId(secretId);
        dto.setOsSecretKey(secretKey);
        return dto;
    }

    @Override
    public File.Storage getDefaultStorage() {
        return File.Storage.COS;
    }
}
