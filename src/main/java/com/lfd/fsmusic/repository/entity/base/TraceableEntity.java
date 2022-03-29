package com.lfd.fsmusic.repository.entity.base;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.lfd.fsmusic.repository.entity.User;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class TraceableEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "creator_uid")
    private User creator;
    @ManyToOne
    @JoinColumn(name = "updater_uid")
    private User updater;

}
