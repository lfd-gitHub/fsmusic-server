package com.lfd.fsmusic.repository.entity;

import javax.persistence.Entity;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;

import lombok.Data;

@Entity
@Data
public class Role extends AbstractEntity {
    private String name;
    private String title;
}
