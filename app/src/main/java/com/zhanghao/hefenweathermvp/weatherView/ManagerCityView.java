package com.zhanghao.hefenweathermvp.weatherView;

import com.zhanghao.hefenweathermvp.Bean.WeatherNow;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/23.
 */
public interface ManagerCityView {
    void getWeatherNowList(List<WeatherNow> weatherNowList);
}
