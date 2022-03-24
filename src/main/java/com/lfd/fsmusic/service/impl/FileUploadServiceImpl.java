package com.lfd.fsmusic.service.impl;

import java.util.Map;
import java.util.Optional;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.UploadMapper;
import com.lfd.fsmusic.repository.FileRepository;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.FileUploadService;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.StorageService;
import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;
import com.lfd.fsmusic.utils.Common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.hutool.crypto.SecureUtil;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    private SettingService settingService;
    private Map<String, StorageService> storageServices;
    private FileRepository fileRepository;
    private UploadMapper fileMapper;

    public FileUploadServiceImpl(UploadMapper fileMapper, FileRepository fileRepository,
            Map<String, StorageService> storageServices, SettingService settingService) {
        this.fileRepository = fileRepository;
        this.storageServices = storageServices;
        this.fileMapper = fileMapper;
        this.settingService = settingService;
    }

    @Override
    public UploadCredentialsDto createCredentials(FileUploadReq req) {

        File file = fileMapper.toEntity(req);
        file.setType(Common.getFileTypeFromExt(req.getExt()));
        file.setStorage(settingService.getDefaultStorage());
        file.setKey(SecureUtil.md5(file.getKey()));

        logger.debug("file => " + file);

        file = fileRepository.save(file);

        UploadCredentialsDto credentialsDto = storageServices.get(settingService.getDefaultStorage().name())
                .genCredentials();
        credentialsDto.setKey(file.getKey());
        credentialsDto.setFileId(file.getId());
        return credentialsDto;
    }

    @Override
    public FileDto finishUpload(String id) {

        Optional<File> file = fileRepository.findById(id);
        if (!file.isPresent()) {
            throw new BizException(EType.FILE_NOT_FOUND);
        }
        File f = file.get();
        f.setStatus(File.Status.UPLOADED);

        return fileMapper.toDto(fileRepository.save(f));
    }
}
