package com.lfd.fsmusic.service.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lfd.fsmusic.repository.entity.Role;
import com.lfd.fsmusic.repository.entity.User.Gender;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
