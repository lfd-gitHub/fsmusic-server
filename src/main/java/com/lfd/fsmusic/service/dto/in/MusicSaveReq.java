package com.lfd.fsmusic.service.dto.in;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MusicSaveReq {
    @NotBlank(message = "名称不能为空")
    private String name;
    private String description;
    private String fileId;
    @NotEmpty(message = "未选择歌手")
    private List<String> artistIds;
}
