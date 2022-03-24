package com.lfd.fsmusic.service.dto.in;

import com.lfd.fsmusic.service.dto.in.common.BaseUserReq;

import lombok.Data;

@Data
public class UserCreateReq extends BaseUserReq {

    private String nickname;
    private String gender;
}
