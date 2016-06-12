package com.zhanghao.hefenweathermvp.location;

import com.zhanghao.hefenweathermvp.weatherDataModel.OnDataRequestListener;
import com.zhanghao.hefenweathermvp.weatherDataModel.RequestData;
import com.zhanghao.hefenweathermvp.weatherDataModel.RequestDataModel;

/**
 * Created by 张浩 on 2016/6/1.
 */
public class MyLocationModel{
    RequestData requestData;

    /**
     * 根据经纬度逆向地理解析
     * @param url
     */
    public void getLocationJson(String url){
        requestData=new RequestDataModel();
        requestData.InitHttp();
        requestData.RequestWeather(url, new OnDataRequestListener() {
            @Override
            public void OnSuccess(String result) {
                    System.out.println(result);
            }

            @Override
            public void Error(String result) {

            }
        });
    }




}
