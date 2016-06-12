package com.zhanghao.hefenweathermvp.preferences;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/15.
 */
public interface Preferences {
    void initialize(Context context);
    void SaveCityCount();
    void SaveCityName(String name);
    int  QueryCityCount();
    List<String> QueryCityName();
    void SaveRefreshTime(int year,int mon,int date,int hour,int min);
    Map<String,Integer> getLastRefreshTime();
}
