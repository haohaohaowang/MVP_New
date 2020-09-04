package com.wwh.mylibrary.net;


import com.google.gson.Gson;
import com.wwh.mylibrary.util.LoggerUtils;


import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;

public class AnalysisHelper {
    /**
     * 根据当前传进来的对象解析数据
     *
     * @param t    要解析的类
     * @param body 访问服务器得到的json字符串
     * @return 返回  Object 类型对象
     * @throws Exception
     */
    public static Object analysisData(Class t, String body) throws Exception {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(body);
        if (jsonObject.getInt("status_code") == 200) {
            return gson.fromJson(body, t);
        } else {
            Response response = new Response();
            response.setStatus(jsonObject.getInt("status_code"));
            response.setMsg(jsonObject.getString("message"));
            response.setResult(new Object());
            return response;
        }
    }


    public static String string(Map<String, String> maps) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> entries = maps.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            String value = entry.getValue();
            String key = entry.getKey();
            stringBuffer.append(key)
                    .append("=")
                    .append(value)
                    .append("&");
        }
        stringBuffer.append(System.currentTimeMillis())
                .append("&");
//                .append(IHelper.APK);
        return stringBuffer.toString();
    }


    public static String tokenString(FormBody formBody) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < formBody.size(); i++) {
            String key = formBody.name(i);
            String value = formBody.value(i);
            stringBuffer.append(key)
                    .append("=")
                    .append(value)
                    .append("&");
        }
        stringBuffer.append(System.currentTimeMillis())
                .append("&");
//                .append(IHelper.APK);
        LoggerUtils.e(AnalysisHelper.class.getName(), "tokenString:" + stringBuffer.toString());
        return stringBuffer.toString();
    }

}
