package com.zhanghao.hefenweathermvp.utils;

/**
 * Created by 张浩 on 2016/5/14.
 */
public class api {
    private static final  String  WEATHER_URL="https://api.heweather.com/x3/weather?cityid=";
    private static final  String  WEATHER_IMAGE_URL="http://files.heweather.com/cond_icon/";
    private static final String GETCITY_URL="http://api.map.baidu.com/geocoder/v2/?ak=nflvXlVj0DxIdo7ytUkbleWtYjeUGMBU" +
            "&callback=renderReverse&";

    /**
     *
     * @param cityCode 城市的代码
     * @return main api
     */
    public static String weatherApi(String cityCode){
        return  WEATHER_URL+cityCode+"&key=15f635c11432494eaaf3a3260d7ae006";
    }

    /**
     *
     * @param code 温度的代码
     * @return weather api
     */
    public static String weatherImageApi(String code){
        return WEATHER_IMAGE_URL+code+".png";
    }


    public static String weatherGetCityName(double a,double b){
        return GETCITY_URL+"location="+a+","+b+"&output=json";
    }


}
