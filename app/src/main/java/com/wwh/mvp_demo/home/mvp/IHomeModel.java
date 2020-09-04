package com.wwh.mvp_demo.home.mvp;


import com.wwh.mvp_demo.base.IBaseModel;

public interface IHomeModel extends IBaseModel {

    /**
     * 获取列表数据
     *
     * @param response
     * @param status   判断刷新或者加载
     */
    void requestList(Object response, int status);


}
