package com.lfd.fsmusic.mapper;

import com.lfd.fsmusic.controller.vo.SettingVo;
import com.lfd.fsmusic.service.dto.SettingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingMapper {

    SettingVo toVo(SettingDto dto);

}
