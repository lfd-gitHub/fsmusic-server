package com.lfd.fsmusic.service;

import com.lfd.fsmusic.repository.entity.Artist;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService {

    Page<ArtistDto> list(Pageable pageable);
    ArtistDto create(ArtistSaveReq req);
    ArtistDto update(String id,ArtistSaveReq req);
    ArtistDto publish(String id);
    ArtistDto block(String id);

}
