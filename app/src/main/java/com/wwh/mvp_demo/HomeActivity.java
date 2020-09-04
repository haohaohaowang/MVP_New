package com.wwh.mvp_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wwh.mvp_demo.base.BaseActivity;
import com.wwh.mvp_demo.home.adapter.HomeAdapter;
import com.wwh.mvp_demo.home.bean.HomeBean;
import com.wwh.mvp_demo.home.mvp.HomePresenter;
import com.wwh.mvp_demo.home.mvp.IHomeView;
import com.wwh.mvp_demo.util.IntentUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView, OnRefreshLoadMoreListener {
    public static HomeActivity instanse;

    @BindView(R.id.rv_home_list)
    RecyclerView rv_home_list;
    @BindView(R.id.srl_mall)
    SmartRefreshLayout srl_mall;
    @BindView(R.id.clpb_fan)
    ContentLoadingProgressBar clpb_fan;
    private List<HomeBean.ResultBean.GoodsListBean> list;
    private HomeAdapter adapter;
    private LinearLayoutManager llm_challenge;
    private int page = 1;

    /**
     * 布局
     *
     * @return
     */
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化 presenter层
     */
    @Override
    protected void initPresenter() {
        instanse = this;
        statusBarColor(this, ContextCompat.getColor(this, R.color.white));//控制顶部状态栏
        mPresenter = new HomePresenter(this);
        mPresenter.init();
    }

    /**
     * 是否隐藏状态栏
     * true 隐藏 fasle 不隐藏
     *
     * @return
     */
    @Override
    protected boolean removeStatusBarBoolean() {
        return false;
    }

    @Override
    protected void destory() {

    }

    @Override
    public void init() {
        llm_challenge = new LinearLayoutManager(mContext);
        llm_challenge.setOrientation(RecyclerView.VERTICAL);
        rv_home_list.setLayoutManager(llm_challenge);
        adapter = new HomeAdapter(R.layout.item_home_list_layout, list);
        rv_home_list.setAdapter(adapter);
        adapter.setEnableLoadMore(false);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        srl_mall.setOnRefreshLoadMoreListener(this);
        srl_mall.setEnableLoadMore(false);
        //请求数据 1为下拉刷新，2为上拉加载
        mPresenter.requestList(params(), 1);

        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            HomeBean.ResultBean.GoodsListBean h = (HomeBean.ResultBean.GoodsListBean) adapter.getItem(position);
            switch (view.getId()) {
                case R.id.ll_home_item:
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("list", h);
//                    IntentUtils.getIntance().intent(mContext, asdf.class, bundle);

                    break;
            }
        });
    }

    /**
     * success
     *
     * @param response Requested data returned successfully
     */
    @Override
    public void requestSuccess(Object response) {
        if (null != srl_mall) {
            srl_mall.finishLoadMore();
        }
        HomeBean homeBean = (HomeBean) response;
        List<HomeBean.ResultBean.GoodsListBean> mall_add = homeBean.getResult().getGoods_list();

        if (null != mall_add && mall_add.size() > 0) {
            if (mall_add.size() < 6) {
                srl_mall.setEnableLoadMore(false);
            } else {
                srl_mall.setEnableLoadMore(true);
            }
            adapter.addData(mall_add);
        }
    }

    /**
     * failure
     *
     * @param status   The Error status code
     * @param errorMsg The Error log information
     */
    @Override
    public void requestFailure(int status, String errorMsg) {

    }


    /**
     * 获取列表成功
     *
     * @param response
     */
    @Override
    public void requestListSuccess(Object response) {
        progressBarHide();
        if (null != srl_mall) {
            srl_mall.finishRefresh();
        }
        HomeBean homeListBean = (HomeBean) response;
        list = homeListBean.getResult().getGoods_list();
        Log.e("TAGS", homeListBean.toString());
        if (null != list && list.size() > 0) {
            if (list.size() < 9) {
                srl_mall.setEnableLoadMore(false);
            } else {
                srl_mall.setEnableLoadMore(true);
            }
            adapter.setNewData(list);
        } else {
            adapter.setNewData(list);
        }
    }

    /**
     * 获取列表失败
     *
     * @param status
     * @param errorMsg
     */
    @Override
    public void requestListFailure(int status, String errorMsg) {
        Log.e("TAGA", errorMsg);
    }

    private Map<String, String> params() {
        Map<String, String> map = new HashMap<>();
        map.put("m", "Api");
        map.put("c", "Goods");
        map.put("a", "goodsList");
        return map;
    }


    private void progressBarHide() {
        if (null != clpb_fan) {
            clpb_fan.hide();
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        mPresenter.requestList(params(), 2);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        mPresenter.requestList(params(), 1);
    }
}
