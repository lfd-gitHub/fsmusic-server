package com.lfd.fsmusic.controller.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseVo {

    private String id;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyyMMddHHmmss")
    private LocalDateTime updatedTime;

}
