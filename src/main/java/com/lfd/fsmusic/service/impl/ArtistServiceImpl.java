package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.mapper.ArtistMapper;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.repository.ArtistRepository;
import com.lfd.fsmusic.repository.entity.Artist;
import com.lfd.fsmusic.service.ArtistService;
import com.lfd.fsmusic.service.base.TraceableBaseServiceImpl;
import com.lfd.fsmusic.service.dto.ArtistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class ArtistServiceImpl extends TraceableBaseServiceImpl<Artist, ArtistDto> implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Override
    protected IEntityMapper<Artist, ArtistDto> getMapper() {
        return artistMapper;
    }

    @Override
    protected Artist beforeCreate(Artist entity) {
        entity.setStatus(Artist.Status.DRAFT);
        return super.beforeCreate(entity);
    }

    @Override
    protected ArtistRepository getRepo() {
        return artistRepository;
    }

    @Override
    public ArtistDto publish(String id) {
        Artist artist = getEntity(id);
        artist.setStatus(Artist.Status.PUBLISHED);
        return artistMapper.toDto(artistRepository.save(artist));
    }

    @Override
    public ArtistDto block(String id) {
        Artist artist = getEntity(id);
        artist.setStatus(Artist.Status.BLOCKED);
        return artistMapper.toDto(artistRepository.save(artist));
    }
}
