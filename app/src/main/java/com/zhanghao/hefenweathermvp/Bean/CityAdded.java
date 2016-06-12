package com.zhanghao.hefenweathermvp.Bean;

import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class CityAdded extends DataSupport {
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "CityAdded{" +
                "cityName='" + cityName + '\'' +
                '}';
    }

    private String cityName;


//    public List<WeatherNow> getWeatherNows() {
//        return weatherNows;
//    }
//
//    public void setWeatherNows(List<WeatherNow> weatherNows) {
//        this.weatherNows = weatherNows;
//    }
//
//    public List<WeatherDaily> getWeatherDailies() {
//        return weatherDailies;
//    }
//
//    public void setWeatherDailies(List<WeatherDaily> weatherDailies) {
//        this.weatherDailies = weatherDailies;
//    }
//
//    public List<WeatherHourly> getWeatherHourlies() {
//        return weatherHourlies;
//    }
//
//    public void setWeatherHourlies(List<WeatherHourly> weatherHourlies) {
//        this.weatherHourlies = weatherHourlies;
//    }

//    private List<WeatherNow> weatherNows=new ArrayList<>();
//    private List<WeatherDaily> weatherDailies=new ArrayList<>();
//    private List<WeatherHourly> weatherHourlies=new ArrayList<>();


}
