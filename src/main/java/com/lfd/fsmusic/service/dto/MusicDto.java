package com.lfd.fsmusic.service.dto;

import com.lfd.fsmusic.repository.entity.Music;
import lombok.Data;

@Data
public class MusicDto extends  BaseDto{

    private String name;
    private Music.Status status;
    private String description;
    private FileDto file;

}
