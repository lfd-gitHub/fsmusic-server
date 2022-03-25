package com.lfd.fsmusic.controller.vo;

import com.lfd.fsmusic.repository.entity.Artist;
import lombok.Data;

import java.util.List;

@Data
public class ArtistVo extends BaseVo{

    private String name;
    private String remark;
    private Artist.Status status;
    private FileVo cover;
    private List<MusicVo> musicList;

}
