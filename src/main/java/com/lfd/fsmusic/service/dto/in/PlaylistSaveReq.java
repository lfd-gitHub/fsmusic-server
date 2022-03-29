package com.lfd.fsmusic.service.dto.in;

import lombok.Data;

import java.util.List;

@Data
public class PlaylistSaveReq {

    private String name;
    private String description;
    private String coverId;
    private String creatorUid;
    private List<String> musicIds;

}
