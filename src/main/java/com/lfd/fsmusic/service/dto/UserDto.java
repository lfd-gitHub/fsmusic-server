package com.lfd.fsmusic.service.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lfd.fsmusic.repository.entity.Role;
import com.lfd.fsmusic.repository.entity.User.Gender;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {

    private String id;
    private String username;
    private String nickname;
    private String password;
    private Gender gender;
    private boolean locked;
    private boolean enabled;
    private String lastLoginIp;
    private LocalDateTime lastLoginTime;
    private List<Role> roles = new ArrayList<>();

}
