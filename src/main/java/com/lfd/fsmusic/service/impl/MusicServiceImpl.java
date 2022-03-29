package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.mapper.MusicMapper;
import com.lfd.fsmusic.repository.MusicRepository;
import com.lfd.fsmusic.repository.entity.Music;
import com.lfd.fsmusic.service.MusicService;
import com.lfd.fsmusic.service.base.SimpleBaseServiceImpl;
import com.lfd.fsmusic.service.dto.MusicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class MusicServiceImpl extends SimpleBaseServiceImpl<Music, MusicDto> implements MusicService {

    private final MusicRepository repository;
    private final MusicMapper mapper;

    @Override
    protected MusicRepository getRepo() {
        return repository;
    }

    @Override
    public MusicMapper getMapper() {
        return mapper;
    }

    @Override
    protected Music beforeCreate(Music entity) {
        entity.setStatus(Music.Status.DRAFT);
        return super.beforeCreate(entity);
    }

    @Override
    public boolean publish(String id) {
        Music music = getEntity(id);
        music.setStatus(Music.Status.PUBLISHED);
        repository.save(music);
        return true;
    }

    @Override
    public boolean close(String id) {
        Music music = getEntity(id);
        music.setStatus(Music.Status.CLOSED);
        repository.save(music);
        return true;
    }
}
