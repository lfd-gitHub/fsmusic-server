package com.lfd.fsmusic.repository;


import com.lfd.fsmusic.repository.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,String> {
}
