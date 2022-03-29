package com.lfd.fsmusic.service.dto.in;

import com.lfd.fsmusic.service.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArtistSaveReq extends BaseDto{

    @NotBlank(message = "歌手名称不能为空")
    private String name;
    private String remark;
    @NotBlank(message = "歌手封面图片不能为空")
    private String coverId;

}

