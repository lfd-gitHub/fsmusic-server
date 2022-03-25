package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.ArtistVo;
import com.lfd.fsmusic.repository.entity.Artist;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = FileMapper.class)
public interface ArtistMapper {

    @Mapping(source = "coverId",target = "cover.id")
    Artist toEntity(ArtistSaveReq req);

    @Mapping(source = "coverId",target = "cover.id")
    Artist updateEntity(@MappingTarget Artist artist, ArtistSaveReq req);

    ArtistDto toDto(Artist entity);

    ArtistVo toVo(ArtistDto artistDto);

}
