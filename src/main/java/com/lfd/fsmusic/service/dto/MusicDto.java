package com.lfd.fsmusic.service.dto;

import com.lfd.fsmusic.repository.entity.Music;
import lombok.Data;

import java.util.List;

@Data
public class MusicDto extends BaseDto{

    private String name;
    private Music.Status status;
    private String description;
    private FileDto file;
    private List<ArtistDto> artistList;

    public MusicDto() {
    }

    public MusicDto(String id) {
        this.id = id;
    }
}
