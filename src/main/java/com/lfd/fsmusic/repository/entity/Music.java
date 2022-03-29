package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Music extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;
    private String description;

    @ManyToMany
    @JoinTable(name = "artist_music", joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    private List<Artist> artistList;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    public enum Status {
        DRAFT, PUBLISHED, CLOSED
    }
}
