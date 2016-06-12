package com.zhanghao.hefenweathermvp.weatherPresenter;

import com.zhanghao.hefenweathermvp.weatherDataModel.HotCityDataModel;
import com.zhanghao.hefenweathermvp.weatherView.AddCityView;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class AddCityPresenter {
    private AddCityView addCityView;
    private HotCityDataModel hotCityDataModel;
    public AddCityPresenter(AddCityView cityView){
        this.addCityView=cityView;
        hotCityDataModel=new HotCityDataModel();
    }
    public void setHotCityData(){
        addCityView.setHotCity(hotCityDataModel.getHotCityList());
    }

}
