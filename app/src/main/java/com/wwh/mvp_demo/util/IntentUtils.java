package com.wwh.mvp_demo.util;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;


public class IntentUtils {
    private static final String TAG = IntentUtils.class.getName();

    private IntentUtils() {
    }

    private static class SendCodeHelper {
        private static IntentUtils INSTANCE = new IntentUtils();
    }

    public static final IntentUtils getIntance() {
        return SendCodeHelper.INSTANCE;
    }

    public void intent(Activity activity, Class clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public void intentResult(Activity activity, Class clazz, Bundle bundle, int result_code) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, result_code);
    }

    public void intentBackResult(Activity activity) {
        Intent intent = new Intent();
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    public void intentBackResultBundle(Activity activity, Bundle bundle) {
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    public void openBrowser(Context context, String url) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            LoggerUtils.d(TAG, "suyan = " + componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            ToastUtil.show(context, "链接错误或无浏览器");
        }
    }

    public Activity getActivity(@NonNull Context from) {
        int limit = 15;
        Context result = from;
        if (result instanceof Activity) {
            return (Activity) result;
        }
        int tryCount = 0;
        while (result instanceof ContextWrapper) {
            if (result instanceof Activity) {
                return (Activity) result;
            }
            if (tryCount > limit) {
                //break endless loop
                return null;
            }
            result = ((ContextWrapper) result).getBaseContext();
            tryCount++;
        }
        return null;
    }

}
