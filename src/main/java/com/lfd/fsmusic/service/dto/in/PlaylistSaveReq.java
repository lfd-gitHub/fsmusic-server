package com.lfd.fsmusic.service.dto.in;

import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistSaveReq {

    private String name;
    private String description;
    private FileDto cover;
    private UserDto creator;
    private List<MusicDto> musicList;

}
