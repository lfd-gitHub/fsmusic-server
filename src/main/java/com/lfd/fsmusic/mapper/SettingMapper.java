package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.SettingVo;
import com.lfd.fsmusic.service.dto.SettingDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SettingMapper {

    SettingVo toVo(SettingDto dto);

}
