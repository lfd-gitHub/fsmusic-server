package com.lfd.fsmusic.service.impl;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.StorageService;
import com.lfd.fsmusic.service.dto.SettingDto;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TreeMap;

@Service("COS")
public class CosStorageServiceImpl implements StorageService {

    private final SettingService settingService;

    public CosStorageServiceImpl(SettingService settingService) {
        this.settingService = settingService;
    }

    @Override
    public UploadCredentialsDto genCredentials() {

        SettingDto setting = settingService.getSiteSettings();

        Response response;
        try {
            response = CosStsClient.getCredential(cfg(setting));
        } catch (IOException e) {
            e.printStackTrace();
            throw BizException.from(EType.SERVER_ERROR);
        }
        UploadCredentialsDto dto = new UploadCredentialsDto();
        dto.setTmpSecretId(response.credentials.tmpSecretId);
        dto.setTmpSecretKey(response.credentials.tmpSecretKey);
        dto.setSessionToken(response.credentials.sessionToken);
        dto.setStartTime(response.startTime);
        dto.setExpiredTime(response.expiredTime);
        return dto;
    }

    private TreeMap<String, Object> cfg(SettingDto setting) {
        TreeMap<String, Object> config = new TreeMap<>();
        config.put("secretId", setting.getOsSecretId());
        config.put("secretKey", setting.getOsSecretKey());

        // 临时密钥有效时长，单位是秒
        config.put("durationSeconds", 1800);
        config.put("allowPrefixes", new String[] {
                "*"
        });
        config.put("bucket", setting.getOsBucket());
        config.put("region", setting.getOsRegion());
        String[] allowActions = new String[] {
                // 简单上传
                "name/cos:PutObject",
                "name/cos:PostObject",
                // 分片上传
                "name/cos:InitiateMultipartUpload",
                "name/cos:ListMultipartUploads",
                "name/cos:ListParts",
                "name/cos:UploadPart",
                "name/cos:CompleteMultipartUpload"
        };
        config.put("allowActions", allowActions);
        return config;
    }

}
