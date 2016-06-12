package com.zhanghao.hefenweathermvp.database;

import com.zhanghao.hefenweathermvp.Bean.CityAdded;
import com.zhanghao.hefenweathermvp.Bean.CityContent;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;

import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/19.
 */
public interface DataBaseQuery {
    void initDataBase();
    List<CityAdded> QueryAddedCity();
    List<WeatherDaily> QueryDaliyCityIds(String name);
    List<WeatherHourly> QueryHourlyCityIds(String name);
    void AddCity(String name);
    boolean isAddedCity(String name);
    List<WeatherNow> QueryWeatherNowList(String name);
    List<WeatherHourly> QueryWeatherHourlyList(String name);
    List<WeatherDaily> QueryWeatherDailyList(String name);
    List<Map<String,String>> SearchCity(String name);
}
