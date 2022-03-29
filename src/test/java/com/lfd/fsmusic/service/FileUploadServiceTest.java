package com.lfd.fsmusic.service;

import com.lfd.fsmusic.repository.entity.WithMockCustomUser;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class FileUploadServiceTest extends BaseServiceTest {

    @Autowired
    FileService fService;

    @Autowired()
    @Qualifier("COS")
    StorageService cosService;

    @Test
    void testFileUri() {
        log.debug("testuri => " + cosService);
    }

    @Test
    @WithMockCustomUser(username = "lfd")
    void testCreateCredentials() {
        log.info("[Test] - testCreateCredentials ========================== ");
        UserDetails user = SpringUtil.getBean(UserDetailsService.class).loadUserByUsername("lfd");
        log.info("[Test] - testCreateCredentials ========================== [" + user + "]");
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
