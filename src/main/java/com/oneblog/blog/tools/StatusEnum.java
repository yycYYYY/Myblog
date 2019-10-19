package com.oneblog.blog.tools;

public enum StatusEnum {
    success(0,"成功"),
    fail(1,"失败"),;

    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code=code;
        this.message=message;
    }

    @Override
    public String toString() {
        return "StatusEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
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
}
