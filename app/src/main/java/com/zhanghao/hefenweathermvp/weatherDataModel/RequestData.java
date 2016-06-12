package com.zhanghao.hefenweathermvp.weatherDataModel;

/**
 * Created by 张浩 on 2016/5/14.
 */
public interface RequestData {
    void InitHttp();
    void RequestWeather(String url,OnDataRequestListener listener);
}
