package com.lfd.fsmusic.service.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseDto {
    private String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
