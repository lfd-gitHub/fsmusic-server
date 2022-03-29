package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.FileVo;
import com.lfd.fsmusic.controller.vo.UploadCredentialsVo;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.mapper.base.IVoMapper;
import com.lfd.fsmusic.mapper.decorator.FileMapperDecorator;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(FileMapperDecorator.class)
public interface FileMapper extends IVoMapper<FileVo, FileDto>, IEntityMapper<File, FileDto> {

    UploadCredentialsVo toUploadVo(UploadCredentialsDto dto);

    File fromReq(FileUploadReq req);

}
