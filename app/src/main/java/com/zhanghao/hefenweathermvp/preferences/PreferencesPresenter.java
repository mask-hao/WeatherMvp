package com.zhanghao.hefenweathermvp.preferences;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class PreferencesPresenter{
    private Preferences preferences;
    public PreferencesPresenter(Context context){
        preferences=new PreferencesModel();
        preferences.initialize(context);
    }
    //保存数量
    public void SaveCityCount(){
        preferences.SaveCityCount();
    }
    //查找CityList
    //查找总的城市的数量
    public int QueryCityCount(){
        return preferences.QueryCityCount();
    }

    public void SaveRefreshedTime(int year,int mon,int date,int hour,int min){
        preferences.SaveRefreshTime(year,mon,date,hour,min);
    }

    public Map<String,Integer> getLastRefreshTime(){
        return  preferences.getLastRefreshTime();
    }
}
