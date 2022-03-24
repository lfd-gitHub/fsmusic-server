package com.lfd.fsmusic.controller.vo;

import com.lfd.fsmusic.repository.entity.File;
import lombok.Data;

@Data
public class SettingVo {
    private String osBucket;
    private String osRegion;
    private File.Storage storage;
}
