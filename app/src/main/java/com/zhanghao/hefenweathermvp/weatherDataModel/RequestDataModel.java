package com.zhanghao.hefenweathermvp.weatherDataModel;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 张浩 on 2016/5/14.
 */
public class RequestDataModel implements RequestData{
    private OkHttpClient okHttpClient;

    /**
     * 初始化okhttp
     */
    @Override
    public void InitHttp() {
        okHttpClient=new OkHttpClient();
    }
    /**
     * okhttp进行数据的访问
     * @param url
     * @param listener
     */
    @Override
    public void RequestWeather(final String url, final OnDataRequestListener listener) {
//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                Request request=new Request.Builder()
//                        .url(url).build();
//                try {
//                    Response response=okHttpClient.newCall(request).execute();
//                    if (response.isSuccessful()){
//                        if (listener!=null){
//                            listener.OnSuccess(response.body().string());
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    if (listener!=null){
//                        listener.Error(e.getMessage());
//                    }
//                }
//            }
//        };
//        Thread thread=new Thread(runnable);
//        thread.start();
//    }
        Request request = new Request.Builder()
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                .url(url).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                if (listener != null) {
                    listener.OnSuccess(response.body().string());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.Error(e.getMessage());
            }
        }
    }

}
