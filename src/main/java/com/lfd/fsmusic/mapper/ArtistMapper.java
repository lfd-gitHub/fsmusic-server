package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.ArtistVo;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.mapper.base.IVoMapper;
import com.lfd.fsmusic.repository.entity.Artist;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = FileMapper.class)
public interface ArtistMapper extends IVoMapper<ArtistVo,ArtistDto>, IEntityMapper<Artist,ArtistDto> {

    @Mapping(source = "coverId",target = "cover.id")
    ArtistDto fromReq(ArtistSaveReq req);

}
