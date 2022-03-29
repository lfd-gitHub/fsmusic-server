package com.lfd.fsmusic.mapper.base;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;
import com.lfd.fsmusic.service.dto.BaseDto;
import org.mapstruct.MappingTarget;

public interface IEntityMapper<Entity extends BaseEntity,Dto extends BaseDto> {

    Entity updateEntity(@MappingTarget Entity old,Dto dto);
    Entity toEntity(Dto d);
    Dto toDto(Entity e);

}
