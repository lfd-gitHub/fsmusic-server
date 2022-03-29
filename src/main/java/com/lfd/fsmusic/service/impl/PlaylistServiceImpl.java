package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.mapper.PlaylistMapper;
import com.lfd.fsmusic.repository.BaseRepository;
import com.lfd.fsmusic.repository.PlaylistRepository;
import com.lfd.fsmusic.repository.entity.Playlist;
import com.lfd.fsmusic.service.PlaylistService;
import com.lfd.fsmusic.service.base.SimpleBaseServiceImpl;
import com.lfd.fsmusic.service.dto.PlaylistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class PlaylistServiceImpl extends SimpleBaseServiceImpl<Playlist, PlaylistDto> implements PlaylistService {

    private final PlaylistRepository repository;
    private final PlaylistMapper mapper;

    @Override
    protected BaseRepository<Playlist> getRepo() {
        return repository;
    }

    @Override
    public PlaylistMapper getMapper() {
        return mapper;
    }

    @Override
    protected Playlist beforeCreate(Playlist entity) {
        entity.setStatus(Playlist.Status.DRAFT);
        entity.setCreator(getCurrentUser());
        return super.beforeCreate(entity);
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
}
