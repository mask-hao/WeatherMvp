package com.zhanghao.hefenweathermvp.database;
import com.zhanghao.hefenweathermvp.Bean.CityAdded;
import com.zhanghao.hefenweathermvp.Bean.CityContent;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class DataBaseQueryModel implements DataBaseQuery {

    @Override
    public void initDataBase() {

    }

    @Override
    public List<CityAdded> QueryAddedCity() {
//        for (CityAdded cityAdded:DataSupport.findAll(CityAdded.class))
//        System.out.println("---->"+cityAdded.toString());
        return DataSupport.findAll(CityAdded.class);
    }

    @Override
    public List<WeatherDaily> QueryDaliyCityIds(String name) {
       return DataSupport.select("id").where("cityadded=?",name).find(WeatherDaily.class);
    }

    @Override
    public List<WeatherHourly> QueryHourlyCityIds(String name) {
        return DataSupport.select("id").where("cityadded=?",name).find(WeatherHourly.class);
    }

//    @Override
//    public List<Integer> QueryAddedCityIds(String name) {
//
//        return DataSupport.select("id").where("cityadded=?",name).find();
//    }

    /**
     *
     * @param name 选择添加的城市的名字
     */
    @Override
    public void AddCity(String name) {
        CityAdded cityAdded=new CityAdded();
        cityAdded.setCityName(name);
        cityAdded.save();
    }

    /**
     *
     * @param name 城市的名字
     * @return 判断选择的城市是否已经添加，添加就return true;
     */
    @Override
    public boolean isAddedCity(String name) {
        List<CityAdded> cityAddedList=DataSupport.findAll(CityAdded.class);
        for (int i=0;i<cityAddedList.size();i++){
            if (cityAddedList.get(i).getCityName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public  List<WeatherNow> QueryWeatherNowList(String name) {
        return DataSupport.where("cityadded=?",name).find(WeatherNow.class);
    }

    @Override
    public List<WeatherHourly> QueryWeatherHourlyList(String name) {
        return DataSupport.where("cityadded=?",name).find(WeatherHourly.class);
    }

    @Override
    public List<WeatherDaily> QueryWeatherDailyList(String name) {
        return DataSupport.where("cityadded=?",name).find(WeatherDaily.class);
    }

    @Override
    public List<Map<String,String>> SearchCity(String name) {
        String prov="";
        List<CityContent> cityContentList =
                DataSupport.select("prov").where("city=?", name).find(CityContent.class);
        List<Map<String,String>> maps=new ArrayList<>();
        if (cityContentList.size()<1){
                Map<String,String> map=new HashMap<>();
                map.put("city","无搜索结果");
                map.put("prov","");
                maps.add(map);
        }else{
            for (int i=0;i<cityContentList.size();i++){
                Map<String,String> map=new HashMap<>();
                prov=cityContentList.get(i).getProv();
                map.put("city",name);
                map.put("prov","--"+prov);
                maps.add(map);
            }
            List<CityContent> cityContentList1=DataSupport.select("city").
                    where("prov=?",prov).find(CityContent.class);
            for (CityContent content:cityContentList1){
                Map<String,String> map1=new HashMap<>();
                map1.put("city",content.getCity());
                map1.put("prov","--"+prov);
                maps.add(map1);
            }
        }
        return maps;
    }


}
