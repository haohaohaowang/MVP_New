package com.wwh.mvp_demo.home.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wwh.mvp_demo.R;
import com.wwh.mvp_demo.home.bean.HomeBean;
import com.wwh.mvp_demo.util.GlideImgloaderHelper;


import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by WangWeiHao on 2020-05-27.
 */
public class HomeAdapter extends BaseQuickAdapter<HomeBean.ResultBean.GoodsListBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.ResultBean.GoodsListBean> data) {
        super(layoutResId, data);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.GoodsListBean item) {
        helper.addOnClickListener(R.id.ll_home_item);
//        ImageView iv_my_pic = helper.getView(R.id.iv_img);
//        GlideImgloaderHelper.getInstance().chansimPicture(mContext, item.getLiveCover(), iv_my_pic);
        helper.setText(R.id.tv_name, item.getGoods_name());
    }

}
