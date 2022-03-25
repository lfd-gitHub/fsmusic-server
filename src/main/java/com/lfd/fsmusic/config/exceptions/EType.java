package com.lfd.fsmusic.config.exceptions;

public enum EType {

    SERVER_ERROR(500, "系统错误"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"), //

    USERNAME_NOT_FOUND(40401001, "用户名不存在"),
    USERNAME_DUPLICATE(40001002, "用户名重复"),
    USER_PASSWORD_NOT_MATCH(40001003, "用户名或密码错误"),
    USER_DISABLED(50001001, "用户未启用"),
    USER_LOCKED(50001002, "用户已锁定"),

    MUSIC_NOT_FOUND(40402001, "歌曲不存在"),
    FILE_NOT_FOUND(40403001, "文件不存在"),
    FILE_UPDATE_NO_PERMIT(40403001, "无权限修改文件"),
    PLAYLIST_NOT_FOUND(40404001, "歌单不存在"),
    ARTIST_NOT_FOUND(40405001, "歌手不存在");

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
