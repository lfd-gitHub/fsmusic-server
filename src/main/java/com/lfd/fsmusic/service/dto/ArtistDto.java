package com.lfd.fsmusic.service.dto;


import com.lfd.fsmusic.repository.entity.Artist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ArtistDto extends BaseDto {

    private String name;
    private String remark;
    private Artist.Status status;
    private FileDto cover;
    private List<MusicDto> musicList;

    public ArtistDto(String id) {
        this.id = id;
    }
}
