package com.lfd.fsmusic.service.dto;


import com.lfd.fsmusic.repository.entity.Artist;
import lombok.Data;

import java.util.List;

@Data
public class ArtistDto extends BaseDto{

    private String name;
    private String remark;
    private Artist.Status status;
    private FileDto cover;
    private List<MusicDto> musicList;

}
