package com.wwh.mylibrary.net;

import java.io.Serializable;

public class HeaderBody implements Serializable {
    private static final long serialVersionUID = System.currentTimeMillis();
    private String token;
    private String time;
    private String verson;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVerson() {
        return verson;
    }

    public void setVerson(String verson) {
        this.verson = verson;
    }

    @Override
    public String toString() {
        return "HeaderBody{" +
                "token='" + token + '\'' +
                ", time='" + time + '\'' +
                ", verson='" + verson + '\'' +
                '}';
    }
}
