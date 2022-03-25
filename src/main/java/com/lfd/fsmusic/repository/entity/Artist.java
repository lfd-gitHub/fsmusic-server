package com.lfd.fsmusic.repository.entity;

import java.util.List;

import javax.persistence.*;

import com.lfd.fsmusic.repository.entity.base.TraceableEntity;

import lombok.Data;

@Data
@Entity
public class Artist extends TraceableEntity {

    private String name;
    private String remark;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cover_id")
    private File cover;

    @ManyToMany
    @JoinTable(name = "artist_music",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"), //
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList;


    public enum Status{
        DRAFT,PUBLISHED,BLOCKED
    }
}
