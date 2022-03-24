package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.dto.PlaylistDto;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {

    Page<PlaylistDto> list(Pageable pageable);

    boolean delete(String id);

    boolean publish(String id);

    boolean close(String id);

    PlaylistDto create(PlaylistSaveReq dto);

    PlaylistDto update(String id, PlaylistSaveReq dto);

}
