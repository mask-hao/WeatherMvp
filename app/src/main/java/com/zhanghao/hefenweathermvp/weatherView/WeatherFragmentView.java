package com.zhanghao.hefenweathermvp.weatherView;

import com.github.mikephil.charting.data.LineData;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import java.util.List;
/**
 * Created by 张浩 on 2016/5/23.
 */
public interface WeatherFragmentView {
    void setUpWeatherNow(List<WeatherNow> weatherNowList);
    void setUpWeatherDaily(List<WeatherDaily> weatherDailyList);
    void setUpWeatherHourly(List<WeatherHourly> weatherHourlyList);
    void setUpWeatherTmpLine(LineData lineDataD,LineData lineDataN);
}
