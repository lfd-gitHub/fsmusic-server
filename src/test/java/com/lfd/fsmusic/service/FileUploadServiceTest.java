package com.lfd.fsmusic.service;

import java.io.IOException;

import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class FileUploadServiceTest {

    @Autowired
    FileUploadService fService;

    @Test
    void testCreateCredentials() throws IOException {
        FileUploadReq req = new FileUploadReq();
        req.setName("test");
        req.setExt("mp3");
        req.setKey("7815696ecbf1c96e6894b779456d330e");
        req.setSize(30000L);
        UploadCredentialsDto dto = fService.createCredentials(req);
        log.info(dto.toString());

        Assertions.assertNotNull(dto.getTmpSecretId());
        Assertions.assertNotNull(dto.getFileId());
    }
}
