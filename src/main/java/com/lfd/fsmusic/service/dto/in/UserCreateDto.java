package com.lfd.fsmusic.service.dto.in;

import com.lfd.fsmusic.service.dto.in.common.ComValidateUser;

import lombok.Data;

@Data
public class UserCreateDto extends ComValidateUser {

    private String nickname;
    private String gender;
}
