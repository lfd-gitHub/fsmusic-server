package com.lfd.fsmusic.controller;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.controller.vo.UploadCredentialsVo;
import com.lfd.fsmusic.mapper.UploadMapper;
import com.lfd.fsmusic.service.FileUploadService;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = "文件上传")
@RestController
@RequestMapping("/api/upload")
@RolesAllowed("ROLE_ADMIN")
public class UploadController {

    private FileUploadService uploadService;
    private UploadMapper mapper;

    public UploadController(FileUploadService uploadService, UploadMapper mapper) {
        this.uploadService = uploadService;
        this.mapper = mapper;
    }

    @PostMapping("/init")
    public ApiResponse initUploadCredentials(@Validated @RequestBody FileUploadReq file) throws IOException {
        UploadCredentialsVo vo = mapper.toUploadVo(uploadService.createCredentials(file));
        return ApiResponse.ok(vo);
    }

    @PostMapping("/finish/{id}")
    public ApiResponse finishUpload(@PathVariable String id) {
        return ApiResponse.ok(mapper.toVo(uploadService.finishUpload(id)));
    }
}
