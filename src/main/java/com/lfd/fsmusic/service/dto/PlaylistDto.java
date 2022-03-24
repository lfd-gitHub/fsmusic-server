package com.lfd.fsmusic.service.dto;

import java.util.List;

import com.lfd.fsmusic.repository.entity.Playlist;

import lombok.Data;

@Data
public class PlaylistDto {

    private String name;
    private String description;
    private FileDto cover;
    private Playlist.Status status;
    private UserDto creator;
    private List<MusicDto> musicList;

}
