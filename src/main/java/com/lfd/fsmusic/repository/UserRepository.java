package com.lfd.fsmusic.repository;

import java.util.Optional;

import com.lfd.fsmusic.repository.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

}
