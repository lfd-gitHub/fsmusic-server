package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.FileVo;
import com.lfd.fsmusic.controller.vo.UploadCredentialsVo;
import com.lfd.fsmusic.mapper.decorator.FileMapperDecorator;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.http.MediaType;

@Mapper(componentModel = "spring")
@DecoratedWith(FileMapperDecorator.class)
public interface FileMapper {

    UploadCredentialsVo toUploadVo(UploadCredentialsDto dto);

    File toEntity(FileUploadReq req);

    FileVo toVo(FileDto dto);

    FileDto toDto(File entity);

    default MediaType toMediaType(String mediaType) {
        return MediaType.valueOf(mediaType);
    }

    default String toSMediaType(MediaType mediaType) {
        return mediaType.toString();
    }

}
