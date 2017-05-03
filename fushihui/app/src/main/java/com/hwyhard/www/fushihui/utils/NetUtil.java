package com.hwyhard.www.fushihui.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hwyhard on 17/4/14.
 * 一个用于网络请求的工具类
 */

public class NetUtil {
    OkHttpClient client = new OkHttpClient();
    //请求并返回json
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        //以字符串的形式返回内容
        return response.body().string();
    }

}
