package com.wwh.mylibrary.net;
import com.wwh.mylibrary.util.LoggerUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者： 王伟浩 on 2020/5/20
 * 邮箱：382443920@qq.com
 * 微信：18515184197
 * 说明: 接口管理类
 */


public class ResquestManager {
    private static final String TAG = ResquestManager.class.getName();
    private static final long MILLISECONDS = 5000L;
//    private IHomeAPIServer iHomeApiServer;
//    private ILoginAPIServer iLoginAPIServer;

    private static class SendCodeHelper {
        private static final ResquestManager INSTANCE = new ResquestManager();
    }

    private ResquestManager() {
    }

    public static final ResquestManager getInstance() {
        return SendCodeHelper.INSTANCE;
    }

    private final OkHttpClient okHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
    }

    /**
     * 请求拦截器，修改请求header
     */
    private class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .build();
            LoggerUtils.e(TAG, "RequestInterceptor.request:" + request.toString());
            LoggerUtils.e(TAG, "RequestInterceptor.request headers:" + request.headers().toString());
            return chain.proceed(request);
        }
    }


    /*------------------------------------新 begin--------------------------------新版本---------------------------------------------------------------------------------*/

    /**
     * 在拦截器里面配置请求头
     */
    private class NewRequestInterceptor implements Interceptor {
        private HeaderBody headerBody;
        private boolean isAddToken = false;

        public NewRequestInterceptor(HeaderBody h, boolean b) {
            this.headerBody = h;
            this.isAddToken = b;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = null;
            if (isAddToken) {
                request = chain.request()
                        .newBuilder()
//                        .addHeader("Token", AsnailApp.getInstance().getToken())
//                        .addHeader("Time", AsnailApp.getInstance().getTime() + "")
                        .build();
//                LoggerUtils.e(TAG, "time:" + AsnailApp.getInstance().getTime() + "");
//                LoggerUtils.e(TAG, "Token:" + AsnailApp.getInstance().getToken());
            } else {
                request = chain.request()
                        .newBuilder()
                        .build();
            }
//                    .addHeader("Accept", "application/vnd.ruizhong." + verson + "+json")
            return chain.proceed(request);
        }
    }

    private OkHttpClient client(HeaderBody h, boolean b) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(MILLISECONDS, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(MILLISECONDS, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(MILLISECONDS, TimeUnit.SECONDS);
        //使用拦截器
        httpClientBuilder.addInterceptor(new NewRequestInterceptor(h, b));
        httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        return httpClientBuilder.build();
    }
    /*------------------------------------新 end---------------------------------------新版本--------------------------------------------------------------------------*/


    /**
     * get 请求
     *
     * @param url
     * @return
     */
    public Call productDetailsCall(String url) {
        //1.okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2构造Request,
        //builder.get()代表的是get请求，url方法里面放的参数是一个网络地址
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        //3将Request封装成call
        return okHttpClient.newCall(request);
    }

    public Call getDataUrl(String url, boolean isAddToken) {
        //1.okhttpClient对象
        OkHttpClient okHttpClient = okHttpClient();
        //2构造Request,
        //builder.get()代表的是get请求，url方法里面放的参数是一个网络地址
        Request.Builder builder = new Request.Builder();
        if (isAddToken) {
            long time = System.currentTimeMillis() / 1000;
//            builder.addHeader("Token", AsnaiUtils.getIntance().emptyParamsMd5(time + ""));
            builder.addHeader("Time", time + "");
        }
        Request request = builder.get()
                .url(url)
                .tag(url)
                .build();
        //3将Request封装成call
        return okHttpClient.newCall(request);
    }


    public Response getPicture(String imgUrl) throws IOException {
        OkHttpClient client = okHttpClient();
        Request request = new Request.Builder()
                .url(imgUrl)
                .build();
        return client.newCall(request).execute();
    }


    /**
     * delete 请求方式
     *
     * @param url
     * @param bodyString
     * @return
     */
    public Call deleteCallRequest(String url, String bodyString) {
        OkHttpClient okHttpClient = okHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        LoggerUtils.d(TAG, "url=" + url);
        LoggerUtils.d(TAG, "bodyString=" + bodyString);
        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody requestBody = RequestBody.create(mediaType, bodyString);
        Request.Builder builder = new Request.Builder();
        Request request = builder.delete(requestBody).url(url).build();
        return okHttpClient.newCall(request);
    }

    /**
     * post 请求
     *
     * @param url
     * @param bodyString
     * @return
     */
    public Call postCallRequest(String url, String bodyString) {
        OkHttpClient okHttpClient = okHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        LoggerUtils.d(TAG, "url=" + url);
        LoggerUtils.d(TAG, "bodyString=" + bodyString);
        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody requestBody = RequestBody.create(mediaType, bodyString);
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(requestBody).url(url).build();
        return okHttpClient.newCall(request);
    }

    /**
     * post 请求
     *
     * @param url
     * @param formBody
     * @return
     */
    public Call postRequest(String url, FormBody formBody) {
        OkHttpClient okHttpClient = okHttpClient();
        LoggerUtils.d(TAG, "url=" + url);
        //创建RequestBody对象，将参数按照指定的MediaType封装
        Request.Builder builder = new Request.Builder();
        Request request = builder.post(formBody).tag(url).url(url).build();
        return okHttpClient.newCall(request);
    }


    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build();
    }

//    public IHomeAPIServer iHomeAPIServer() {
//        if (iHomeApiServer == null) {
//            Retrofit retrofit = retrofit();
//            iHomeApiServer = retrofit.create(IHomeAPIServer.class);
//        }
//        return iHomeApiServer;
//    }
//    public ILoginAPIServer iLoginApiServer() {
//        if (iLoginAPIServer == null) {
//            Retrofit retrofit = retrofit();
//            iLoginAPIServer = retrofit.create(ILoginAPIServer.class);
//        }
//        return iLoginAPIServer;
//    }


}
