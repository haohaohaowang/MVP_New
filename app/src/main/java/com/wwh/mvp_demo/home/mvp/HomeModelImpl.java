package com.wwh.mvp_demo.home.mvp;


import android.util.Log;

import com.bumptech.glide.RequestManager;
import com.wwh.mvp_demo.api.net.ResquestManager;
import com.wwh.mvp_demo.home.bean.HomeBean;
import com.wwh.mvp_demo.home.bean.HomeListBean;
import com.wwh.mvp_demo.util.IHelp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModelImpl implements IHomeModel {
    private static final String TAG = HomeModelImpl.class.getName();
    private IHomeListener listener;

    public HomeModelImpl(IHomeListener l) {
        this.listener = l;
    }

    @Override
    public void requestDefault(Object object) {

    }


    /**
     * 获取列表数据
     *
     * @param response
     */
    @Override
    public void requestList(Object response, int status) {
        Map<String, String> map = (Map<String, String>) response;
//        Call<HomeBean> call = ResquestManager.getInstance().iHomeAPIServer().requestList(
//                map.get("m"),
//                map.get("c"),
//                map.get("a"));
        Call<HomeBean> call = ResquestManager.getInstance().iHomeAPIServer().requestList(
                map);


        call.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                HomeBean homeListBean = response.body();
                if (null == homeListBean) {
                    listener.requestListFailure(IHelp.NETWORK_404, IHelp.ERROR_ORDER_MSG);
                    return;
                }
                if (homeListBean.getStatus() == 1) {
                    //status：1 下拉刷新  2：上拉加载
                    if (status == 1) {
                        listener.requestListSuccess(homeListBean);
                    } else if (status == 2) {
                        listener.requestSuccess(homeListBean);
                    }


                } else {
                    if (status == 1) {
                        listener.requestListFailure(homeListBean.getStatus(), homeListBean.getMsg());
                    } else if (status == 2) {
                        listener.requestFailure(homeListBean.getStatus(), homeListBean.getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {
                Log.e("TAGS", "失败");
            }
        });

    }
}
