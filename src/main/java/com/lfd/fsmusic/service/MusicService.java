package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.MusicDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MusicService extends BaseService<MusicDto> {

    Page<MusicDto> search(String name, Pageable pageable);

    boolean publish(String id);

    boolean close(String id);

}
