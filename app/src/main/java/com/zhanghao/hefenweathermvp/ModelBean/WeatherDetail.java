package com.zhanghao.hefenweathermvp.ModelBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩 on 2016/5/18.
 */
public class WeatherDetail implements Serializable {
    private static List<GsonWeather.WeatherNow> weatherNowList;
    private static List<List<GsonWeather.DailyForecast>>  dailyForecastList;
    private static List<List<GsonWeather.HourlyForecast>> hourlyForecastList;
    private static WeatherDetail weatherDetailInstance;
    public static WeatherDetail getInstance(){
        weatherNowList=new ArrayList<>();
        dailyForecastList=new ArrayList<>();
        hourlyForecastList=new ArrayList<>();
        if (weatherDetailInstance==null){
            synchronized (WeatherDetail.class){
                if (weatherDetailInstance==null){
                    weatherDetailInstance=new WeatherDetail();
                }
            }
        }
        return weatherDetailInstance;
    }
    public void AddWeatherNowList(GsonWeather.WeatherNow weatherNow){
        weatherNowList.add(weatherNow);
    }
    public void AddWeatherDailyList(List<GsonWeather.DailyForecast> dailyList){
        dailyForecastList.add(dailyList);
    }
    public void AddWeatherHourlyList(List<GsonWeather.HourlyForecast> hourlyList){
        hourlyForecastList.add(hourlyList);
    }

    public GsonWeather.WeatherNow getWeatherNow(int location){
        return weatherNowList.get(location);
    }
    public List<GsonWeather.DailyForecast> getDailyWeather(int location){
        return dailyForecastList.get(location);
    }

    public List<GsonWeather.HourlyForecast> getHourlyWeather(int location){
      return hourlyForecastList.get(location);
    }

    public String SeeSize(){
        return "["+"weatherNow:"+weatherNowList.size()+"\n"+
                "Day:"+dailyForecastList.size()+"\n"+
                "Hour:"+hourlyForecastList.size()+"\n"
                +"]";
    }


}
