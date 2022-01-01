package com.lfd.fsmusic.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.lfd.fsmusic.repository.entity.Role;
import com.lfd.fsmusic.repository.entity.User;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@ActiveProfiles()
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository repo;
    @Autowired
    RoleRepository repoRole;

    @Test
    void testGetByUsername() {
        User user = new User();
        user.setUsername("uuuu");
        user.setNickname("unick");
        user.setEnabled(true);
        user.setLocked(true);
        user.setPassword("123456");
        user.setGender(User.Gender.MALE);
        user.setLastLoginIp("127.0.0.1");
        user.setLastLoginTime(LocalDateTime.now());

        System.out.println("--------------");
        System.out.println(repo.toString());
        repo.save(user);
        Optional<User> userQuery = repo.findByUsername("uuuu");
        if (userQuery.isPresent()) {
            System.out.println("roles : " + userQuery.get().getRoles());
            System.out.println(userQuery.toString());
        }
        System.out.println("--------------");
        List<Role> roleList = repoRole.findAll();
        System.out.println("roles:" + roleList.toString());

    }
}
