package com.lfd.fsmusic.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.repository.BaseRepository;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.repository.entity.base.BaseEntity;
import com.lfd.fsmusic.service.dto.BaseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SimpleBaseServiceImpl<Entity extends BaseEntity, Dto extends BaseDto>
        implements BaseService<Dto> {

    protected abstract BaseRepository<Entity> getRepo();

    protected abstract IEntityMapper<Entity, Dto> getMapper();

    protected Entity beforeCreate(Entity entity) {
        return entity;
    }

    protected Entity beforeUpdate(Entity entity) {
        return entity;
    };

    protected User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return user;
    }

    protected Entity getEntity(String id) {
        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
        Class<?> clazz = (Class<?>) t.getActualTypeArguments()[0];
        Optional<Entity> entity = getRepo().findById(id);
        if (!entity.isPresent()) {
            throw BizException.from(EType.valueOf(clazz.getSimpleName().toUpperCase() + "_NOT_FOUND"));
        }
        return entity.get();
    }

    public Page<Dto> search(String name, Pageable pageable) {
        String match = String.format("%%%s%%", StrUtil.nullToEmpty(name));
        log.debug("match = " + match);
        return search((r, q, cb) -> cb.and(cb.like(r.get("name"), match)), pageable);
    }

    public Page<Dto> search(Specification<Entity> spec, Pageable pageable) {
        return getRepo().findAll(spec, pageable).map(getMapper()::toDto);
    }

    public Page<Dto> list(Pageable pageable) {
        return getRepo().findAll(pageable).map(getMapper()::toDto);
    }

    @Override
    public Dto create(Dto dto) {
        Entity transientEntity = getMapper().toEntity(dto);
        transientEntity = beforeCreate(transientEntity);
        Entity persistentEntity = getRepo().save(transientEntity);
        return getMapper().toDto(persistentEntity);
    }

    @Override
    public boolean delete(String id) {
        getRepo().delete(getEntity(id));
        return true;
    }

    @Override
    public Dto update(String id, Dto dto) {
        Entity old = getEntity(id);
        Entity transientEntity = getMapper().updateEntity(old, dto);
        transientEntity = beforeUpdate(transientEntity);
        Entity persistentEntity = getRepo().save(transientEntity);
        return getMapper().toDto(persistentEntity);
    }

    @Override
    public Dto get(String id) {
        return getMapper().toDto(getEntity(id));
    }
}
