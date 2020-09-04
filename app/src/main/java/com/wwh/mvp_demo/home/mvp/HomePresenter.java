package com.wwh.mvp_demo.home.mvp;


import com.wwh.mvp_demo.base.BasePresenter;

/**
 * Created by WangWeiHao on 2020-05-20.
 */
public class HomePresenter extends BasePresenter<IHomeView> implements IHomeModel, IHomeListener {

    private HomeModelImpl model;

    public HomePresenter(IHomeView mView) {
        super(mView);
        model = new HomeModelImpl(this);
    }


    @Override
    protected void removeView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void requestSuccess(Object object) {
        if (null != mView) {
            mView.requestSuccess(object);
        }
    }

    @Override
    public void requestFailure(int status, String errorMsg) {
        if (null != mView) {
            mView.requestFailure(status, errorMsg);
        }
    }

    @Override
    public void requestDefault(Object object) {
        if (null != model) {
            model.requestDefault(object);
        }
    }


    /**
     * 获取列表成功
     *
     * @param response
     */
    @Override
    public void requestListSuccess(Object response) {
        if (null != mView) {
            mView.requestListSuccess(response);
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
        if (null != mView) {
            mView.requestListFailure(status, errorMsg);
        }
    }

    /**
     * 获取列表数据
     *
     * @param response
     */
    @Override
    public void requestList(Object response, int status) {
        if (null != model) {
            model.requestList(response, status);
        }
    }
}
