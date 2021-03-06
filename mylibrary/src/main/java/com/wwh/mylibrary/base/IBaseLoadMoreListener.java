package com.wwh.mylibrary.base;

public interface IBaseLoadMoreListener extends IBaseListener {
    /**
     * 加载更多数据成功
     * @param response 返回的数据
     */
    void requestLoadMoreSuccess(Object response);

    /**
     * 加载更多数据失败
     * @param status 失败状态码
     * @param errorMsg 错误日志
     */
    void requestLoadMoreFailure(int status, String errorMsg);
}
