package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.PlaylistVo;
import com.lfd.fsmusic.repository.entity.Playlist;
import com.lfd.fsmusic.service.dto.PlaylistDto;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistVo toVo(PlaylistDto dto);

    PlaylistDto toDto(Playlist entity);

    Playlist toEntity(PlaylistSaveReq req);

    Playlist updateEntity(@MappingTarget Playlist music, PlaylistSaveReq dto);

}
