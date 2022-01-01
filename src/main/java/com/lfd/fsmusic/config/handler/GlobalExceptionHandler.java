package com.lfd.fsmusic.config.handler;

import java.nio.file.AccessDeniedException;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public ApiResponse bizHandler(BizException e) {
        return ApiResponse.error(e);
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResponse defaultHandler(Exception e) {
        return ApiResponse.error();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse deniedHandler(Exception e) {
        return ApiResponse.error(EType.FORBIDDEN.getCode(), EType.FORBIDDEN.getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse badHandler(Exception e) {
        return ApiResponse.error(EType.BAD_REQUEST.getCode(), EType.BAD_REQUEST.getMsg());
    }

}
