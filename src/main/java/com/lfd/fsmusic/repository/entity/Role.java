package com.lfd.fsmusic.repository.entity;

import javax.persistence.Entity;

import com.lfd.fsmusic.repository.entity.base.BaseEntity;

import lombok.Data;

@Entity
@Data
public class Role extends BaseEntity {
    private String name;
    private String title;
}
