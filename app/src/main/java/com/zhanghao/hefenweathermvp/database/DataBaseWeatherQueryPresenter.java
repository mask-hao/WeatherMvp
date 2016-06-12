package com.zhanghao.hefenweathermvp.database;

import com.zhanghao.hefenweathermvp.Bean.CityAdded;
import com.zhanghao.hefenweathermvp.Bean.CityContent;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.weatherView.AddCityView;
import com.zhanghao.hefenweathermvp.weatherView.ManagerCityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/23.
 */
public class DataBaseWeatherQueryPresenter {
    private DataBaseQuery dataBaseQuery;
    private ManagerCityView managerCityView;
    private AddCityView addCityView;
    public DataBaseWeatherQueryPresenter(ManagerCityView cityView){
        this.managerCityView=cityView;
        dataBaseQuery=new DataBaseQueryModel();
    }

    public DataBaseWeatherQueryPresenter(AddCityView addCityView){
        this.addCityView=addCityView;
        dataBaseQuery=new DataBaseQueryModel();
    }

    /**
     * 已添加城市的weatherNow
     */
    public void getWeatherNowList() {
        List<CityAdded> cityAddedList = dataBaseQuery.QueryAddedCity();
        List<WeatherNow> weatherNowList=new ArrayList<>();
        for (CityAdded cityAdded : cityAddedList) {
            weatherNowList.addAll(dataBaseQuery.QueryWeatherNowList(cityAdded.getCityName()));
        }
        managerCityView.getWeatherNowList(weatherNowList);
    }

    /**
     * 根据用户输入城市的名字来查寻
     * @param name 名字
     */
    public void  getSearchCityName(String name){
        addCityView.setSearchResult(dataBaseQuery.SearchCity(name));
    }


}
