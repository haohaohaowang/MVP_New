package com.wwh.mylibrary.util;

/**
 * Created by WangWeiHao on 2020-05-25.
 */
public interface IHelp {

    int NETWORK_SUCCESS = 200;
    int NETWORK_ERROR = 1002;//网络错误
    String NETWORK_ERROR_MSG = "访问失败";
    int SSL_ERROR = 1005;//证书出错
    String SSL_ERROR_MSG = "证书出错";
    int UNKNOWN = 1000;//未知错误
    int PARSE_ERROR = 1001;//数据解析错误
    String PARSE_ERROR_MSG = "访问失败";
    int NETWORK_404 = 404; //其它错误
    int NETWORK_999 = 999;
    String ERROR_ORDER_MSG = "请求失败";
    int NET_0 = 0;
}
