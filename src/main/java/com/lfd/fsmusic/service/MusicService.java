package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.in.MusicEditDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MusicService {

    Page<MusicDto> list(Pageable pageable);
    boolean delete(String id);

    boolean publish(String id);
    boolean close(String id);

    MusicDto create(MusicEditDto dto);
    MusicDto update(String id,MusicEditDto dto);

}
