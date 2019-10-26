package com.oneblog.blog.model.vo;

import java.io.Serializable;

import static com.oneblog.blog.model.enums.BaseResponseEnums.ERROR;
import static com.oneblog.blog.model.enums.BaseResponseEnums.FAIL;
import static com.oneblog.blog.model.enums.BaseResponseEnums.SUCCESS;

public class BaseResponseVO implements Serializable {

    private int code;
    private String message;
    private Object content;

    BaseResponseVO success(){
        return new BaseResponseVO(SUCCESS.getCode(),SUCCESS.getMessage(),null);
    }

    BaseResponseVO fail(){
        return new BaseResponseVO(FAIL.getCode(),FAIL.getMessage(),null);
    }

    BaseResponseVO error(){
        return new BaseResponseVO(ERROR.getCode(),ERROR.getMessage(),null);
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
