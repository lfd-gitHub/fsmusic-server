package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.MusicVo;
import com.lfd.fsmusic.repository.entity.Music;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.in.MusicSaveReq;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = FileMapper.class)
public interface MusicMapper {

    MusicDto toDto(Music music);

    MusicVo toVo(MusicDto music);

    Music toEntity(MusicSaveReq music);

    Music updateEntity(@MappingTarget Music music, MusicSaveReq dto);
}
