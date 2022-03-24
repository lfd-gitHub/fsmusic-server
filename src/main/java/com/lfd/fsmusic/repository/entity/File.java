package com.lfd.fsmusic.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
public class File extends AbstractEntity {

    private String name;
    @Column(name = "`key`")
    private String key;
    private Long size;
    private String ext;

    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Status status = Status.UPLOADING;
    @Enumerated(EnumType.STRING)
    private Storage storage;

    public enum Type {
        AUDIO, IMAGE, VIDEO, OTHER
    }

    public enum Status {
        UPLOADED, UPLOADING, CANCELED
    }

    public enum Storage {
        OSS, COS
    }
}
