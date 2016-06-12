package com.zhanghao.hefenweathermvp.weatherDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 张浩 on 2016/5/14.
 */
public class HotCityDataModel {
    //todo anyOther things
    private String []hotCity = {
            "北京", "上海", "广州", "深圳",
            "武汉", "南京", "杭州", "西安",
            "郑州", "成都", "东莞", "沈阳",
            "天津", "哈尔滨", "长沙", "福州",
            "石家庄", "苏州", "重庆", "无锡",
            "济南", "大连", "佛山", "厦门",
            "南昌", "太原", "长春", "合肥",
            "浦东", "青岛", "汕头", "昆明",
            "南宁", "香港", "澳门", "台北"};
    public List<String> getHotCityList(){
        List<String> list=new ArrayList<>();
        Collections.addAll(list, hotCity);
        return list;
    }




}
