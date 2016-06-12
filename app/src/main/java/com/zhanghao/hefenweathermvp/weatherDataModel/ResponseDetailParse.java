package com.zhanghao.hefenweathermvp.weatherDataModel;

import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/14.
 */
public interface ResponseDetailParse {
    void getWeatherNow(GsonWeather.WeatherNow weatherNow);
    void getForecastDaily(List<GsonWeather.DailyForecast> dailyForecastList);
    void getForecastHourly(List<GsonWeather.HourlyForecast> hourlyForecasts);
    void getErrorMessage(String error);
}
