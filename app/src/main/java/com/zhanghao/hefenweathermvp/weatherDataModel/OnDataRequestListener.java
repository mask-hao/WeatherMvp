package com.zhanghao.hefenweathermvp.weatherDataModel;

/**
 * Created by 张浩 on 2016/5/14.
 */
public interface OnDataRequestListener {
    void OnSuccess(String result);
    void Error(String result);
}
