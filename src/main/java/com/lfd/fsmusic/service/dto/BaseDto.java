package com.lfd.fsmusic.service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseDto {
    protected String id;
    protected LocalDateTime createdTime;
    protected LocalDateTime updatedTime;
}
