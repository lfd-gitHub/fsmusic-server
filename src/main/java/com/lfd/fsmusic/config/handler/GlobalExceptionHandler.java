package com.lfd.fsmusic.config.handler;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.lfd.fsmusic.base.ApiResponse;
import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = BizException.class)
    public ApiResponse bizHandler(BizException e) {
        logger.error("[bizHandler] = {}", e.getMessage());
        return ApiResponse.error(e);
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResponse defaultHandler(Exception e) {
        logger.error("[defaultHandler] = {}", e.getMessage());
        return ApiResponse.error();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse deniedHandler(Exception e) {
        logger.error("[deniedHandler] = {}", e.getMessage());
        return ApiResponse.error(EType.FORBIDDEN.getCode(), EType.FORBIDDEN.getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse badHandler(MethodArgumentNotValidException e) {
        logger.error("[badHandler] = {}", e.getMessage());
        List<ObjectError> eList = e.getBindingResult().getAllErrors();
        int code = EType.BAD_REQUEST.getCode();
        String msg = EType.BAD_REQUEST.getMsg();
        if (!eList.isEmpty()) {
            ObjectError err = eList.get(0);
            code = EType.BAD_REQUEST.getCode();
            msg = err.getDefaultMessage();
        }
        return ApiResponse.error(code, msg);
    }

}
