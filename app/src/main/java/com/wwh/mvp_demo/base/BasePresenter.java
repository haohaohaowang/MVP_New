package com.wwh.mvp_demo.base;

/**
 * 作者： 王伟浩 on 2020/5/20.
 * 邮箱：382443920@qq.com
 * 微信：18515184197
 * 说明: Presenter中间层
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected T mView;

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    public void init() {
        if (mView != null) mView.init();
    }

    protected abstract void removeView();

}
