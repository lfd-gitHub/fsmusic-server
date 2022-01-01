package com.lfd.fsmusic.config.exceptions;

public enum EType {

    SERVER_ERROR(500, "系统错误"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"), //
    USERNAME_DUPLICATE(40001001, "用户名重复"),
    USERNAME_NOT_FOUND(40401001, "用户名不存在");

    private final Integer code;
    private final String msg;

    EType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
