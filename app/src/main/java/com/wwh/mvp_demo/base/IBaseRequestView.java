package com.wwh.mvp_demo.base;

/**
 * 作者： 王伟浩 on 2020/5/20.
 * 邮箱：382443920@qq.com
 * 微信：18515184197
 * 说明: 请求回调到服务器的侦听器接口
 */

public interface IBaseRequestView extends IBaseView {
    /**
     * success
     * @param response Requested data returned successfully
     */
    void requestSuccess(Object response);

    /**
     * failure
     * @param status The Error status code
     * @param errorMsg The Error log information
     */
    void requestFailure(int status, String errorMsg);
}
