package com.wwh.mvp_demo.home.mvp;


import com.wwh.mvp_demo.base.IBaseRequestView;

public interface IHomeView extends IBaseRequestView {
    /**
     * 获取列表成功
     *
     * @param response
     */
    void requestListSuccess(Object response);

    /**
     * 获取列表失败
     *
     * @param status
     * @param errorMsg
     */
    void requestListFailure(int status, String errorMsg);
}
