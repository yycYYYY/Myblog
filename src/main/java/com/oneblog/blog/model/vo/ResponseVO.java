package com.oneblog.blog.model.vo;

public class ResponseVO extends BaseResponseVO {
    ResponseVO(int code, String message, Object content) {
        super(code, message, content);
    }
}
