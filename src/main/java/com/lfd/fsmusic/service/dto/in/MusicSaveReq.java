package com.lfd.fsmusic.service.dto.in;

import com.lfd.fsmusic.service.dto.FileDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MusicSaveReq {
    @NotBlank(message = "名称不能为空")
    private String name;
    private String description;
    private FileDto file;
}
