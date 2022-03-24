package com.lfd.fsmusic.service.dto;

import com.lfd.fsmusic.repository.entity.File;

import lombok.Data;

@Data
public class FileDto extends BaseDto {

    private String name;
    private String key;
    private Long size;
    private String ext;

    private File.Type type;
    private File.Status status;

}
