package com.wwh.mylibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wwh.mylibrary.util.LoggerUtils;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {
    private static final String TAG = BaseFragment.class.getName();
    protected T mPresenter;
    protected Context mContext;
    //Fragment的View加载完毕的标记
    protected boolean isViewCreated;
    //Fragment对用户可见的标记
    protected boolean isUIVisible;
    private View view;
    protected Bundle bundle;
    //是否加过数据了
    private boolean isFirst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle = savedInstanceState;
        if (null == bundle) {
            bundle = new Bundle();
        }
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(provideContentViewId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = savedInstanceState;
        if (null == bundle) {
            bundle = new Bundle();
        }
        isViewCreated = true;
        isUIVisible = true;
        isCanLoadData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

//        super.onSaveInstanceState(outState);
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            //如果确认加载过数据之后 再次进入界面做刷新
            if (isFirst){
                refreshData();
            }
            isCanLoadData();
            LoggerUtils.d(TAG, "isVisibleToUser=" + isVisibleToUser);
        } else {
            isUIVisible = false;
            LoggerUtils.d(TAG, "isVisibleToUser=" + isVisibleToUser);
        }
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            LoggerUtils.d(TAG, "isCanLoadData");
            initPresenter();
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
            isFirst=true;
        }
        LoggerUtils.d(TAG, "isCanLoadData is false");
    }

    /**
     * 刷洗数据 使用
     */
    protected abstract void refreshData();

    /**
     * 布局
     *
     * @return
     */
    protected abstract int provideContentViewId();

    /**
     * 初始化 presenter层
     */
    protected abstract void initPresenter();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected abstract void loadData();


}
