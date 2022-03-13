package com.lfd.fsmusic.controller.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lfd.fsmusic.repository.entity.User.Gender;

import lombok.Data;

@Data
public class UserVo {
    private String username;
    private String nickname;
    private Gender gender;
    private boolean locked;
    private boolean enabled;
    private String lastLoginIp;
    private LocalDateTime lastLoginTime;
    private List<RoleVo> roles = new ArrayList<>();
}
