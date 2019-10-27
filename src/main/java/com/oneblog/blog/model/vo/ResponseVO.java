package com.oneblog.blog.model.vo;

import static com.oneblog.blog.model.enums.BaseResponseEnums.SUCCESS;

public class ResponseVO extends BaseResponseVO {

    ResponseVO(int code, String message, Object content) {
        super(code, message, content);
    }
    public static ResponseVO SUCCESS(Object content){
        return new ResponseVO(SUCCESS.getCode(),SUCCESS.getMessage(),content);
    }
}
