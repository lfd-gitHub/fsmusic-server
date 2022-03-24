package com.lfd.fsmusic.service.dto;

import com.lfd.fsmusic.repository.entity.File;
import lombok.Data;

@Data
public class SettingDto {

    private String osBucket;
    private String osRegion;
    private File.Storage storage;
    private String osSecretId;
    private String osSecretKey;

}
