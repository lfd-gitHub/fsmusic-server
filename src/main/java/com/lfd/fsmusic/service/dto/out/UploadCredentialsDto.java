package com.lfd.fsmusic.service.dto.out;

import lombok.Data;

@Data
public class UploadCredentialsDto {

    private String tmpSecretId;
    private String tmpSecretKey;
    private String sessionToken;
    private String key;
    private Integer size;
    private String fileId;
    private Long startTime;
    private Long expiredTime;

}
