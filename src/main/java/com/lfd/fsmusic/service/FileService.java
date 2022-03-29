package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.base.BaseService;
import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

public interface FileService extends BaseService<FileDto> {

    UploadCredentialsDto createCredentials(FileUploadReq file);

    FileDto finishUpload(String id);

}
