package com.wwh.mvp_demo.util;

import android.util.Log;

public class LoggerUtils {
    /**
     * 控制是否 需要开启 日志打印输出的开关
     */
    private static boolean isOpen=false;

    public static void e(final String TAG, String msg){
        if (isOpen){
            Log.e(TAG,">>打印的日志信息>>"+msg);
        }
    }

    public static void d(final String TAG, String msg){
        if (isOpen){
            Log.d(TAG,">>打印的日志信息>>"+msg);
        }
    }
}
