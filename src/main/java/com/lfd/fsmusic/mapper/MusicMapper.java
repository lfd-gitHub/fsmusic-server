package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.MusicVo;
import com.lfd.fsmusic.repository.entity.Music;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.in.MusicEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MusicMapper {

    MusicDto toDto(Music music);
    MusicVo toVo(MusicDto music);
    Music toEntity(MusicEditDto music);
    Music updateEntity(@MappingTarget Music music, MusicEditDto dto);
}
