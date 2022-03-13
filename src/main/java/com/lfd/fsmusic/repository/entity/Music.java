package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Music extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;
    private String description;

    public enum Status{
        DRAFT,PUBLISH,CLOSE
    }
}


