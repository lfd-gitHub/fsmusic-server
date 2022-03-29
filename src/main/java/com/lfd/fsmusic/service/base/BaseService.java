package com.lfd.fsmusic.service.base;

import com.lfd.fsmusic.service.dto.BaseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<Dto extends BaseDto> {

    Page<Dto> list(Pageable pageable);

    Dto create(Dto dto);

    boolean delete(String id);

    Dto update(String id, Dto dto);

    Dto get(String id);

}
