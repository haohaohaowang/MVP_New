package com.wwh.mylibrary.base.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by WangWeiHao on 2020-05-28.
 * 保存用户信息实体类
 */
public class UserSQLBean extends LitePalSupport implements Serializable {
    private static final long serialVersionUID = System.currentTimeMillis();
    private String user_id;
    private String user_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "UserSQLBean{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
