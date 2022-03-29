package com.lfd.fsmusic.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;
import com.lfd.fsmusic.mapper.FileMapper;
import com.lfd.fsmusic.mapper.base.IEntityMapper;
import com.lfd.fsmusic.repository.FileRepository;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.repository.entity.User;
import com.lfd.fsmusic.service.FileService;
import com.lfd.fsmusic.service.SettingService;
import com.lfd.fsmusic.service.StorageService;
import com.lfd.fsmusic.service.base.SimpleBaseServiceImpl;
import com.lfd.fsmusic.service.dto.FileDto;
import com.lfd.fsmusic.service.dto.in.FileUploadReq;
import com.lfd.fsmusic.service.dto.out.UploadCredentialsDto;
import com.lfd.fsmusic.utils.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy, @Autowired})
public class FileServiceImpl extends SimpleBaseServiceImpl<File, FileDto> implements FileService {

    private final SettingService settingService;
    private final Map<String, StorageService> storageServices;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    protected FileRepository getRepo() {
        return null;
    }

    @Override
    protected IEntityMapper<File, FileDto> getMapper() {
        return null;
    }

    @Override
    public FileDto create(FileDto dto) {
        throw new RuntimeException("Needn't Implement");
    }

    @Override
    public FileDto update(String id, FileDto dto) {
        throw new RuntimeException("Needn't Implement");
    }

    @Override
    public boolean delete(String id) {
        throw new RuntimeException("Needn't Implement");
    }

    @Override
    public UploadCredentialsDto createCredentials(FileUploadReq req) {

        File file = fileMapper.fromReq(req);
        file.setType(Common.getFileTypeFromExt(req.getExt()));
        file.setStorage(settingService.getDefaultStorage());
        file.setKey(SecureUtil.md5(file.getKey()));
        User user = getCurrentUser();
        file.setCreator(user);
        file.setUpdater(user);
        log.debug("file => " + file);

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
            throw BizException.from(EType.FILE_NOT_FOUND);
        }

        if (!file.get().getCreator().getId().equals(getCurrentUser().getId())) {
            throw BizException.from(EType.FILE_UPDATE_NO_PERMIT);
        }

        File f = file.get();
        f.setStatus(File.Status.UPLOADED);

        return fileMapper.toDto(fileRepository.save(f));
    }

}
