package com.zhanghao.hefenweathermvp.weatherView;

import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/14.
 */
public interface AddCityView {
    void hideDialog();
    void showDialog(String str);
    void setHotCity(List<String> HotCityList);
    void setSearchResult(List<Map<String,String>> list);
}
