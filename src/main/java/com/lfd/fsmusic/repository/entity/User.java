package com.lfd.fsmusic.repository.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.lfd.fsmusic.repository.entity.base.AbstractEntity;

import lombok.Data;
import lombok.ToString;
@Entity
@Data
@ToString(callSuper=true)
public class User extends AbstractEntity{
    
    private String username;
    private String nickname;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean locked;
    private boolean enabled;
    private String lastLoginIp;
    private LocalDateTime lastLoginTime;

    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", 
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public static enum Gender{
        MALE,FEMALE,UNKNOWN
    }
}