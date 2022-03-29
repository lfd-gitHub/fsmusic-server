package com.lfd.fsmusic.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;

import lombok.Data;

@Entity
@Data
public class Playlist extends BaseEntity {

    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "cover_id")
    private File cover;

    @ManyToOne
    @JoinColumn(name = "creator_uid")
    protected User creator;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "playlist_music", joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList;

    public enum Status {
        DRAFT, PUBLISHED, CLOSED
    }

}