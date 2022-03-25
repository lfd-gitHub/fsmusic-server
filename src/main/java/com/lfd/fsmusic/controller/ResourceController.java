package com.lfd.fsmusic.controller;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.controller.vo.UploadCredentialsVo;
import com.lfd.fsmusic.mapper.FileMapper;
import com.lfd.fsmusic.service.FileService;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@Api(tags = "资源管理")
@RestController
@RequestMapping("/api/res")
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class ResourceController {

    private final FileService fileService;
    private final FileMapper mapper;

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/upload/init")
    public ApiResponse uploadInitCredentials(@Validated @RequestBody FileUploadReq file) throws IOException {
        UploadCredentialsVo vo = mapper.toUploadVo(fileService.createCredentials(file));
        return ApiResponse.ok(vo);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/upload/finish/{id}")
    public ApiResponse finishUpload(@PathVariable String id) {
        return ApiResponse.ok(mapper.toVo(fileService.finishUpload(id)));
    }
}
