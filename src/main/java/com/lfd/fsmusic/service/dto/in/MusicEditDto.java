package com.lfd.fsmusic.service.dto.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MusicEditDto {
    @NotBlank(message = "名称不能为空")
    private String name;
    private String description;
}
