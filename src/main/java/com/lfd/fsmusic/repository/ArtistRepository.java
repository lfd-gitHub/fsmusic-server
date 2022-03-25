package com.lfd.fsmusic.repository;

import com.lfd.fsmusic.repository.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,String> {
}
