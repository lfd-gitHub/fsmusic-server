package com.lfd.fsmusic.service.dto.in;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FileUploadReq {

    @NotBlank(message = "key不能为空")
    private String key;
    @NotBlank(message = "文件名不能为空")
    private String name;
    @NotBlank(message = "后缀不能为空")
    private String ext;
    private Long size;

}
