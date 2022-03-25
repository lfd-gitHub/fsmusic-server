package com.lfd.fsmusic.service;

import cn.hutool.extra.spring.SpringUtil;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.dto.in.UserCreateReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public abstract class BaseServiceTest {

    @BeforeAll
    void before() {
        log.info("[Test] - before ==========================");
        UserCreateReq userCreateRequest = new UserCreateReq();
        userCreateRequest.setUsername("lfd");
        userCreateRequest.setNickname("lfd");
        userCreateRequest.setPassword("123456");
        userCreateRequest.setGender(User.Gender.MALE.toString());
        SpringUtil.getBean(UserService.class).create(userCreateRequest);
    }

}
