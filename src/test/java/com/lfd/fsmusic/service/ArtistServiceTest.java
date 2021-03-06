package com.lfd.fsmusic.service;

import com.lfd.fsmusic.mapper.ArtistMapper;
import com.lfd.fsmusic.repository.entity.WithMockCustomUser;
import com.lfd.fsmusic.service.dto.ArtistDto;
import com.lfd.fsmusic.service.dto.in.ArtistSaveReq;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class ArtistServiceTest extends BaseServiceTest {

    @Autowired
    ArtistService artistService;
    @Autowired
    FileService fileService;
    @Autowired
    ArtistMapper artistMapper;

    String fileId;
    String artistId;

    @BeforeEach
    void initFile() {
        FileUploadReq req = new FileUploadReq();
        req.setName("test");
        req.setExt("mp3");
        req.setKey("7815696ecbf1c96e6894b779456d330e");
        req.setSize(30000L);
        UploadCredentialsDto dto = fileService.createCredentials(req);
        log.info("[initFile] = {}", dto.toString());
        this.fileId = dto.getFileId();
    }

    @Test
    @WithMockCustomUser()
    void create() {
        ArtistSaveReq artistSaveReq = new ArtistSaveReq();
        artistSaveReq.setName("test");
        log.info("[create] fileId = {}", fileId);
        artistSaveReq.setCoverId(fileId);
        artistSaveReq.setRemark("test rm");
        ArtistDto artistDto = artistService.create(artistMapper.fromReq(artistSaveReq));
        this.artistId = artistDto.getId();
        log.info("[create] = {}", artistDto.toString());
    }

    @Test
    @WithMockCustomUser()
    void get() {
        ArtistSaveReq artistSaveReq = new ArtistSaveReq();
        artistSaveReq.setName("test");
        log.info("[create] fileId = {}", fileId);
        artistSaveReq.setCoverId(fileId);
        artistSaveReq.setRemark("test rm");
        ArtistDto artistDto = artistService.get(artistId);
        log.info("[create] = {}", artistDto.toString());
    }

}