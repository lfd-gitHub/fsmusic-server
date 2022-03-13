package com.lfd.fsmusic.repository;

import com.lfd.fsmusic.repository.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music,String> {
}
