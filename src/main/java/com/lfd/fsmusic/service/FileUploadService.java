package com.lfd.fsmusic.service;

import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;

public interface FileUploadService {

    public UploadCredentialsDto createCredentials(FileUploadReq file);

    public FileDto finishUpload(String id);

}
