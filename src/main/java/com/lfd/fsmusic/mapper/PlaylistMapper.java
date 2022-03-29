package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.PlaylistVo;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.mapper.base.IVoMapper;
import com.lfd.fsmusic.repository.entity.Playlist;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.PlaylistDto;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = FileMapper.class)
public interface PlaylistMapper extends IVoMapper<PlaylistVo, PlaylistDto>, IEntityMapper<Playlist,PlaylistDto> {

    @Mapping(source = "creatorUid",target = "creator.id")
    @Mapping(source = "coverId",target = "cover.id")
    @Mapping(source = "musicIds",target = "musicList")
    PlaylistDto fromReq(PlaylistSaveReq req);


    default List<MusicDto> mapMusicList(List<String> musicIds) {
        return musicIds.stream().map(MusicDto::new).collect(Collectors.toList());
    }
}
