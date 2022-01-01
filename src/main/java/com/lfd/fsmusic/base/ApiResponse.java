package com.lfd.fsmusic.base;

import com.lfd.fsmusic.config.exceptions.BizException;
import com.lfd.fsmusic.config.exceptions.EType;

import lombok.Data;

/**
 * ApiResponse
 */
@Data
public class ApiResponse {

    private Integer code;
    private String errorMsg;
    private Object data;

    public ApiResponse(int code, String errorMsg, Object data) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static ApiResponse ok() {
        return new ApiResponse(0, "", null);
    }

    public static ApiResponse ok(Object result) {
        return new ApiResponse(0, "", result);
    }

    public static ApiResponse error(BizException e) {
        return new ApiResponse(e.getCode(), e.getMessage(), null);
    }

    public static ApiResponse error() {
        return new ApiResponse(
                EType.SERVER_ERROR.getCode(), //
                EType.SERVER_ERROR.getMsg(), //
                null);
    }

    public static ApiResponse error(EType e) {
        return new ApiResponse(e.getCode(), e.getMsg(), null);
    }

    public static ApiResponse error(int code, String msg) {
        return new ApiResponse(code, msg, null);
    }

}
