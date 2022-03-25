package com.lfd.fsmusic.service.impl;

import java.util.Optional;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.MusicMapper;
import com.lfd.fsmusic.repository.MusicRepository;
import com.lfd.fsmusic.repository.entity.Music;
import com.lfd.fsmusic.service.MusicService;
import com.lfd.fsmusic.service.dto.MusicDto;
import com.lfd.fsmusic.service.dto.in.MusicSaveReq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class MusicServiceImpl implements MusicService {

    private final MusicRepository repository;
    private final MusicMapper mapper;

    private Music getEntity(String id) {
        Optional<Music> music = repository.findById(id);
        if (!music.isPresent())
            throw BizException.from(EType.MUSIC_NOT_FOUND);
        return music.get();
    }

    public Page<MusicDto> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
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

    @Override
    public MusicDto create(MusicSaveReq dto) {
        Music music = repository.save(mapper.toEntity(dto));
        return mapper.toDto(music);
    }

    @Override
    public MusicDto update(String id, MusicSaveReq dto) {
        Music musicOld = getEntity(id);
        Music music = repository.save(mapper.updateEntity(musicOld, dto));
        return mapper.toDto(music);
    }
}
