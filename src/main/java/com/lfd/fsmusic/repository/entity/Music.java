package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Data
public class Music extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;
    private String description;

    @OneToOne
    private File file;

    public enum Status{
        DRAFT,PUBLISH,CLOSE
    }
}


