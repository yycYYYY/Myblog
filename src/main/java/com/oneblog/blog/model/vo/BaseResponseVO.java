package com.oneblog.blog.model.vo;

import java.io.Serializable;

import static com.oneblog.blog.model.enums.BaseResponseEnums.ERROR;
import static com.oneblog.blog.model.enums.BaseResponseEnums.FAIL;
import static com.oneblog.blog.model.enums.BaseResponseEnums.SUCCESS;

public class BaseResponseVO{

    private int code;
    private String message;
    private Object content;

    public static BaseResponseVO success(Object content){
        return new BaseResponseVO(SUCCESS.getCode(),SUCCESS.getMessage(),content);
    }

    public static BaseResponseVO fail(Object content){
        return new BaseResponseVO(FAIL.getCode(),FAIL.getMessage(),content);
    }

    public static BaseResponseVO error(Object content){
        return new BaseResponseVO(ERROR.getCode(),ERROR.getMessage(),content);
    }

    BaseResponseVO(int code, String message, Object content) {

        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
