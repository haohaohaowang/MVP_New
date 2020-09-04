package com.wwh.mvp_demo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.StrictMode;

import org.litepal.LitePal;

/**
 * Created by WangWeiHao on 2020-05-28.
 */
public class App extends Application {
    private static Context context;
    private static App instance;
    private int mActivityCount = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);//数据库初始化
        context = getApplicationContext();
        instance = this;
        //android 7.0系统解决拍照的问题android.os.FileUriExposedException:file:///storage/emulated/0/...
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    /**
     * 将字体设置为系统默认大小
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    public static App getInstance() {
        return instance;
    }

    public int getActivityCount() {
        return mActivityCount;
    }
}
