package com.zhanghao.hefenweathermvp.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class PreferencesModel implements Preferences {

    private  SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String cityNameXml="CityName";
    private String cityCountXml="CityCount";
    private String RefreshedTime="Time";
    private Context context;

    @Override
    public void initialize(Context context) {
        this.context=context;
    }

    /**
     * 保存添加的城市的数量
     */
    @Override
    public void SaveCityCount() {
        int a=QueryCityCount();
        editor=context.getSharedPreferences(cityCountXml,Context.MODE_PRIVATE).edit();
        editor.putInt("count",a+1);
        editor.commit();
    }

    /*
    * 保存城市的名字
    * */
    @Override
    public void SaveCityName(String name) {
        int a=QueryCityCount();
        editor=context.getSharedPreferences(cityNameXml,Context.MODE_PRIVATE).edit();
        editor.putString("name"+a,name);
        editor.commit();
    }

    /**
     *
     * @return 返回城市的数量
     */
    @Override
    public int  QueryCityCount() {
        sharedPreferences=context.getSharedPreferences(cityCountXml,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("count",0);
    }

    /**
     *
     * @return 带有name+i 的城市List；
     */
    @Override
    public List<String> QueryCityName() {
        List<String> cityNameList=new ArrayList<>();
        int a=QueryCityCount();
        Log.d("a", String.valueOf(a));
        sharedPreferences=context.getSharedPreferences(cityNameXml,Context.MODE_PRIVATE);
        if (a>0)
        for(int i=0;i<a;i++){
            cityNameList.add(sharedPreferences.getString("name"+i,""));
        }
        return cityNameList;
    }

    @Override
    public void SaveRefreshTime(int year,int mon, int date, int hour, int min) {
            editor=context.getSharedPreferences(RefreshedTime,Context.MODE_PRIVATE).edit();
            editor.putInt("year",year);
            editor.putInt("mon",mon);
            editor.putInt("date",date);
            editor.putInt("hour",hour);
            editor.putInt("min",min);
            editor.commit();
    }

    @Override
    public Map<String, Integer> getLastRefreshTime() {
        sharedPreferences=context.getSharedPreferences(RefreshedTime,Context.MODE_PRIVATE);
        Map<String,Integer> map=new HashMap<>();
        map.put("year",sharedPreferences.getInt("year",0));
        map.put("mon",sharedPreferences.getInt("mon",0));
        map.put("date",sharedPreferences.getInt("date",0));
        map.put("hour",sharedPreferences.getInt("hour",0));
        map.put("min",sharedPreferences.getInt("min",0));
        return map;
    }
}
