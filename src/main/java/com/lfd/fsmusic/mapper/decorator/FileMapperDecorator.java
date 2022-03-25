package com.lfd.fsmusic.mapper.decorator;

import com.lfd.fsmusic.mapper.FileMapper;
import com.lfd.fsmusic.repository.entity.File;
import com.lfd.fsmusic.service.StorageService;
import com.lfd.fsmusic.service.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;


public abstract class FileMapperDecorator implements FileMapper {

    @Autowired
    @Qualifier("delegate")
    private FileMapper delegate;

    @Autowired
    private Map<String, StorageService> storageServices;

    @Override
    public FileDto toDto(File file) {
        FileDto fileDto = delegate.toDto(file);

        if (fileDto == null) {
            return null;
        }
        if (fileDto.getStorage() == null) {
            return null;
        }
        fileDto.setUri(storageServices.get(fileDto.getStorage().name()).getFileUri(fileDto.getKey()));
        return fileDto;
    }
}
