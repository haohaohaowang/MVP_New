package com.wwh.mylibrary.util;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.wwh.mylibrary.R;


/**
 * 说明: Gilde 图片加载 帮助类
 */

public class GlideImgloaderHelper {
    private static final String TAG = GlideImgloaderHelper.class.getName();

    private static class SendCodeHelper {
        private static final GlideImgloaderHelper INSTANCE = new GlideImgloaderHelper();
    }

    private GlideImgloaderHelper() {
    }

    public static final GlideImgloaderHelper getInstance() {
        return SendCodeHelper.INSTANCE;
    }


    /**
     * 头像 使用
     *
     * @param context    上下文
     * @param pictureUrl 图片地址
     * @param imageView
     */
    public void headPicture(Context context, String pictureUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(pictureUrl)
                    .apply(headOptions())
                    .into(imageView);
        } catch (Exception e) {
            LoggerUtils.e(TAG, "----------GlideImgloaderHelper.headPicture-----------" + e.toString());
        }
    }

    //头像
    private RequestOptions headOptions() {
        RequestOptions options = RequestOptions.circleCropTransform();
        options.centerCrop()
                .error(R.drawable.ic_launcher)//更换项目中的默认图片
                .placeholder(R.drawable.ic_launcher);
        return options;
    }

    /**
     * @param context    上下文
     * @param pictureUrl 图片地址
     * @param imageView
     */
    public void chansimPicture(Context context, String pictureUrl, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(pictureUrl)
                    .into(imageView);
        } catch (Exception e) {
            LoggerUtils.e(TAG, "----------GlideImgloaderHelper.chansimPicture-----------" + e.toString());
        }
    }

    //默认
    private RequestOptions chansimOptions() {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.ic_launcher)//更换项目中的默认图片
                .error(R.drawable.ic_launcher);
        return options;
    }


    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public void loadIntoUseFitWidth(Context context, final String imageUrl, final ImageView imageView) {
        try {
            Glide.with(context)
                    .load(imageUrl)
                    .apply(chansimOptions())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (imageView == null) {
                                return false;
                            }
                            if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
                            int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                            params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                            imageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .into(imageView);
        } catch (Exception e) {
            LoggerUtils.e(TAG, "----------GlideImgloaderHelper.loadIntoUseFitWidth-----------" + e.toString());
        }
    }


    public void loadGif(Context context, int reg, ImageView image) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(android.R.drawable.stat_notify_error)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context)
                .load(reg)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        LoggerUtils.e(TAG, "----------onLoadFailed---------");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        LoggerUtils.e(TAG, "----------onResourceReady---------");
                        return false;
                    }
                })
                .apply(options)
                .into(image);
    }

    public void fitsSystemWindows(boolean isTranslucentStatus, View view) {
        if (isTranslucentStatus) {
            view.getLayoutParams().height = calcStatusBarHeight(view.getContext());
        }
    }

    public int calcStatusBarHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }


}
