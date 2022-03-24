package com.lfd.fsmusic.controller.vo;

import com.lfd.fsmusic.repository.entity.File;
import lombok.Data;

@Data
public class FileVo extends BaseVo {

    private String name;
    private String key;
    private Long size;
    private String ext;

    private File.Type type;
    private File.Status status;

}
