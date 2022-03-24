package com.lfd.fsmusic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlaylistServiceTest {

    @Autowired
    PlaylistService service;

    @Autowired
    UserService userService;

    @Autowired
    MusicService musicService;

    @Test
    void create() {

    }
}