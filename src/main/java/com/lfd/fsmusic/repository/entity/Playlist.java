package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;
import com.lfd.fsmusic.repository.entity.base.TraceableEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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