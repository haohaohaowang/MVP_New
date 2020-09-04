package com.wwh.mylibrary.net;

public class ErrorResponse {
    public int code;
    private String message;

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
