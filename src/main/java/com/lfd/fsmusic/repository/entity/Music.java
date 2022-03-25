package com.lfd.fsmusic.repository.entity;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Music extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;
    private String description;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    public enum Status {
        DRAFT, PUBLISHED, CLOSED
    }
}
