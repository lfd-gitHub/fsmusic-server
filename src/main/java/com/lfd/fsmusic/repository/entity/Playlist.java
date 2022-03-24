package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Playlist extends AbstractEntity {

    private String name;
    private String description;

    @OneToOne
    private File cover;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    private User creator;

    @ManyToMany
    @JoinTable(name = "playlist_music",joinColumns = @JoinColumn(name = "playlist_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList;

    public enum Status{
        DRAFT,PUBLISH,CLOSE
    }

}