package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.MusicVo;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.mapper.base.IVoMapper;
import com.lfd.fsmusic.repository.entity.Music;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.in.MusicSaveReq;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = FileMapper.class)
public interface MusicMapper extends IVoMapper<MusicVo, MusicDto>, IEntityMapper<Music,MusicDto> {

    @Mapping(source = "fileId",target = "file.id")
    @Mapping(source = "artistIds",target = "artistList")
    MusicDto fromReq(MusicSaveReq music);


    default List<ArtistDto> mapArtistList(List<String> artistIds) {
        return artistIds.stream().map(ArtistDto::new).collect(Collectors.toList());
    }

}
