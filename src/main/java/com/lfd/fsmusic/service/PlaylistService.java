package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.PlaylistDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService extends BaseService<PlaylistDto> {

    Page<PlaylistDto> search(String name, Pageable pageable);

    boolean delete(String id);

    boolean publish(String id);

    boolean close(String id);

}
