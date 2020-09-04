package com.wwh.mvp_demo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.wwh.mvp_demo.help.AndroidWorkaround;
import com.wwh.mvp_demo.util.StatusBarUtils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;


/**
 * Activity 基类
 *
 * @param <T>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected T mPresenter;
    protected Context mContext;
    protected Bundle bundle;
    protected Object mActivityData;
    public static final String INTENT_DATA = "INTENT_DATA";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == bundle) {
            bundle = new Bundle();
        }
        setContentView(provideContentViewId());
        removeStatusBar();

        ButterKnife.bind(this);
        mContext = this;
//        hideBottomUIMenu();
        initPresenter();
    }

    /**
     * 去掉状态栏
     */
    public void removeStatusBar() {
        if (removeStatusBarBoolean()) {
            solveNavigationBar(getWindow());
        } else {
            if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
                AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
            }
        }
    }

    public void solveNavigationBar(Window window) {
        //保持布局状态
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                //布局位于状态栏下方
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                //全屏
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                //隐藏导航栏
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (Build.VERSION.SDK_INT >= 19) {
            uiOptions |= 0x00001000;
        } else {
            uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }

    /**
     * 设置主题颜色
     *
     * @param activity
     */
    public void statusBar(Activity activity) {
        StatusBarUtils.setStatusBarColor(activity, Color.WHITE);
        if (!StatusBarUtils.setStatusBarDarkTheme(activity, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(activity, 0xFFFFFFFF);
        }
    }

    /**
     * 设置主题颜色
     *
     * @param activity
     */
    public void statusBarColor(Activity activity,int color) {
        StatusBarUtils.setStatusBarColor(activity, color);
        if (!StatusBarUtils.setStatusBarDarkTheme(activity, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtils.setStatusBarColor(activity, 0xFFFFFFFF);
        }
    }



    public Object getActivityData() {
        if (mActivityData != null) {
            return mActivityData;
        }
        Intent intent = getIntent();
        try {
            if (intent != null) {
                mActivityData = (Object) intent.getSerializableExtra(INTENT_DATA);
            }
        } catch (ClassCastException e) {

        }
        return mActivityData;
    }

    public void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            Window _window = this.getWindow();
            WindowManager.LayoutParams params = _window.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
            _window.setAttributes(params);
        }
    }

    public void hideBottomUIMenu1() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState = bundle;
//        super.onSaveInstanceState(outState);
//    }

    @Override
    protected void onDestroy() {
        mPresenter.removeView();
        destory();
        super.onDestroy();
    }

    @Override
    public final void onBackPressed() {
        if (!onBackPressedInternal()) {
            super.onBackPressed();
        }
    }

    public boolean onBackPressedInternal() {
        return false;
    }

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
     * 是否隐藏状态栏
     * true 隐藏 fasle 不隐藏
     *
     * @return
     */
    protected abstract boolean removeStatusBarBoolean();

    protected abstract void destory();


}
