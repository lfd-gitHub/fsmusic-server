package com.lfd.fsmusic.controller.vo;

import com.lfd.fsmusic.repository.entity.Music;

import lombok.Data;

@Data
public class MusicVo extends BaseVo {

    private String name;
    private Music.Status status;
    private String description;
    private FileVo file;

}
