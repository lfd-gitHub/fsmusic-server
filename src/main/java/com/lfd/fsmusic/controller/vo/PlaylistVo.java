package com.lfd.fsmusic.controller.vo;

import java.util.List;

import com.lfd.fsmusic.repository.entity.Playlist;

import lombok.Data;

@Data
public class PlaylistVo {

    private String name;
    private String description;
    private FileVo cover;
    private Playlist.Status status;
    private UserVo creator;
    private List<MusicVo> musicList;

}
