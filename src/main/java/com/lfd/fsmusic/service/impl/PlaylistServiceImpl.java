package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.PlaylistMapper;
import com.lfd.fsmusic.repository.PlaylistRepository;
import com.lfd.fsmusic.repository.entity.Playlist;
import com.lfd.fsmusic.service.PlaylistService;
import com.lfd.fsmusic.service.dto.PlaylistDto;
import com.lfd.fsmusic.service.dto.in.PlaylistSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository repository;
    private final PlaylistMapper mapper;


    private Playlist getEntity(String id) {
        Optional<Playlist> playlist = repository.findById(id);
        if (!playlist.isPresent())
            throw BizException.from(EType.PLAYLIST_NOT_FOUND);
        return playlist.get();
    }

    public Page<PlaylistDto> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean publish(String id) {
        Playlist list = getEntity(id);
        list.setStatus(Playlist.Status.PUBLISHED);
        repository.save(list);
        return true;
    }

    @Override
    public boolean close(String id) {
        Playlist list = getEntity(id);
        list.setStatus(Playlist.Status.CLOSED);
        repository.save(list);
        return true;
    }

    @Override
    public PlaylistDto create(PlaylistSaveReq dto) {
        Playlist saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public PlaylistDto update(String id, PlaylistSaveReq dto) {
        Playlist listOld = getEntity(id);
        Playlist list = repository.save(mapper.updateEntity(listOld, dto));
        return mapper.toDto(list);
    }
}
