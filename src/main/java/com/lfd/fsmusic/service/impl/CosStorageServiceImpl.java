package com.lfd.fsmusic.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.StorageService;
import com.lfd.fsmusic.service.dto.SettingDto;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.http.HttpMethodName;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("COS")
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class CosStorageServiceImpl implements StorageService {

    private final SettingService settingService;
    private final COSClient cosClient;

    /**
     * 文档
     * https://cloud.tencent.com/document/product/436/10199
     * https://cloud.tencent.com/document/product/436/35217
     * 
     * @param fileKey
     * @return
     */
    @Override
    public String getFileUri(String fileKey) {

        SettingDto setting = settingService.getSiteSettings();

        log.debug("[getFileUri] client =>" + cosClient);

        // 调用 COS 接口之前必须保证本进程存在一个 COSClient 实例，如果没有则创建
        // 详细代码参见本页：简单操作 -> 创建 COSClient

        // 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = setting.getOsBucket();
        // 对象键(Key)是对象在存储桶中的唯一标识。详情请参见
        // [对象键](https://cloud.tencent.com/document/product/436/13324)

        // 设置签名过期时间(可选), 若未进行设置则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);

        // 填写本次请求的参数，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的参数
        Map<String, String> params = new HashMap<String, String>();

        // 填写本次请求的头部，需与实际请求相同，能够防止用户篡改此签名的 HTTP 请求的头部
        Map<String, String> headers = new HashMap<String, String>();

        // 请求的 HTTP 方法，上传请求用 PUT，下载请求用 GET，删除请求用 DELETE
        HttpMethodName method = HttpMethodName.GET;

        URL url = cosClient.generatePresignedUrl(bucketName, fileKey, expirationDate, method, headers, params);
        log.debug("[getFileUri] uri = " + url.toString());

        // 确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();

        return url.toString();
    }

    /**
     * 文档：
     * [获取cos临时秘钥](https://cloud.tencent.com/document/product/436/14048)
     *
     * @return
     */
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
