package com.lfd.fsmusic.repository;

import com.lfd.fsmusic.repository.entity.File;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, String> {

}
