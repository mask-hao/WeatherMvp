package com.zhanghao.hefenweathermvp.database;

import android.content.Context;

import com.zhanghao.hefenweathermvp.Bean.CityAdded;
import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;

import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/13.
 */
public interface DataBase {
    void CopyCityDataBase(Context context);
    void InitDataBase();
    void MakeDir(Context context);
    //void InsertIntoDataBase(List<City.CityContent> list);
    Map<String,String> QueryCityIdFromDataBase(String name);
    //void SaveCityAdded(String CityName);

    void SaveWeatherNow(GsonWeather.WeatherNow weatherNow, String cityName,String Mode);
    void SaveWeatherDaily(List<GsonWeather.DailyForecast> dailyList,String cityName,String Mode);
    void SaveWeatherHourly(List<GsonWeather.HourlyForecast> hourlyForecasts,String cityName,String Mode);

//    void UpdateWeatherNow(GsonWeather.WeatherNow weatherNow, String cityName);
//    void UpdateWeatherDaily(List<GsonWeather.DailyForecast> dailyList,String cityName);
//    void UpdateWeatherHourly(List<GsonWeather.HourlyForecast> hourlyForecasts,String cityName);
}
