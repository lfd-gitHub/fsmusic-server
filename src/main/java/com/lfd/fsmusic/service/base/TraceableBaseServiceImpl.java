package com.lfd.fsmusic.service.base;

import com.lfd.fsmusic.repository.entity.base.TraceableEntity;
import com.lfd.fsmusic.service.dto.BaseDto;

public abstract class TraceableBaseServiceImpl<Entity extends TraceableEntity,Dto extends BaseDto> extends SimpleBaseServiceImpl<Entity,Dto>{

    @Override
    protected Entity beforeCreate(Entity entity) {
        entity.setCreator(getCurrentUser());
        entity.setUpdater(getCurrentUser());
        return entity;
    }

    @Override
    protected Entity beforeUpdate(Entity entity) {
        entity.setUpdater(getCurrentUser());
        return entity;
    }

}
