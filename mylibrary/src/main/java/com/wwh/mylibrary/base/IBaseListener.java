package com.wwh.mylibrary.base;



public interface IBaseListener {
    /**
     * success
     * @param object Requested data returned successfully
     */
    void requestSuccess(Object object);

    /**
     * failure
     * @param status The Error status code
     * @param errorMsg The Error log information
     */
    void requestFailure(int status, String errorMsg);
}
