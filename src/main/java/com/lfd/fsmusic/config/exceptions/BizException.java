package com.lfd.fsmusic.config.exceptions;

public class BizException extends RuntimeException {

    private final Integer code;

    public BizException(EType type) {
        super(type.getMsg());
        this.code = type.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
