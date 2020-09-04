package com.wwh.mvp_demo.api.home;

import com.wwh.mvp_demo.home.bean.HomeBean;
import com.wwh.mvp_demo.home.bean.HomeListBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by WangWeiHao on 2020-05-24.
 */
public interface IHomeAPIServer {
    /**
     * 获取首页列表
     *
     * @param params
     * @return
     */
//    @POST(IHomeAPI.URL_LIST)
//    @FormUrlEncoded
//    Call<HomeListBean> requestList(@FieldMap Map<String, String> params);//post请求方法，因接口是get请求所有只做参考


    /**
     * 获取首页列表
     *
     * @return
     */

//    @HTTP(method = "GET", path = IHomeAPI.URL_LIST + "/m/{m}/c/{c}/a/{a}", hasBody = false)
//    Call<HomeBean> requestList(@Path("m") String m,
//                               @Path("c") String c,
//                               @Path("a") String a);

//    @HTTP(method = "GET", path = IHomeAPI.URL_LIST + "/m{m}/c/{c}/a/{a}", hasBody = false)
    @GET(IHomeAPI.URL_LIST)
    Call<HomeBean> requestList(@QueryMap Map<String, String> params);


}
