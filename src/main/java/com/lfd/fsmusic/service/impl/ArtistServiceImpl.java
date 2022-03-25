package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.ArtistMapper;
import com.lfd.fsmusic.repository.ArtistRepository;
import com.lfd.fsmusic.repository.entity.Artist;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.ArtistService;
import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class ArtistServiceImpl extends BaseService implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;


    @Override
    public Page<ArtistDto> list(Pageable pageable) {
        return artistRepository.findAll(pageable).map(artistMapper::toDto);
    }

    @Override
    public ArtistDto create(ArtistSaveReq req) {
        Artist artist = artistMapper.toEntity(req);
        artist.setStatus(Artist.Status.DRAFT);
        artist.setCreator(getCurrentUser());
        artist.setUpdater(getCurrentUser());
        return artistMapper.toDto(artistRepository.save(artist));
    }

    public Artist getEntity(String id){
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if(!optionalArtist.isPresent()){
            throw BizException.from(EType.ARTIST_NOT_FOUND);
        }
        return optionalArtist.get();
    }

    @Override
    public ArtistDto update(String id, ArtistSaveReq req) {
        Artist oldArtist = getEntity(id);
        File coverOnlyId = null;
        if(oldArtist.getCover() != null){
            coverOnlyId = new File();
            coverOnlyId.setId(oldArtist.getCover().getId());
        }
        oldArtist.setCover(coverOnlyId);
        Artist artist = artistMapper.updateEntity(oldArtist,req);
        return artistMapper.toDto(artistRepository.save(artist));
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
