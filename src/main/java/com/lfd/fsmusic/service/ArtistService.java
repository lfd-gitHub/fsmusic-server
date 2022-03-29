package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.ArtistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArtistService extends BaseService<ArtistDto> {

    Page<ArtistDto> search(String name, Pageable pageable);
    ArtistDto publish(String id);
    ArtistDto block(String id);

}
