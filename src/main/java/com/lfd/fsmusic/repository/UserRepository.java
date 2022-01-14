package com.lfd.fsmusic.repository;

import java.util.Optional;

import com.lfd.fsmusic.repository.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

}
