package com.zhanghao.hefenweathermvp.weatherPresenter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.zhanghao.hefenweathermvp.location.MyLocation;
import com.zhanghao.hefenweathermvp.location.MyLocationModel;
import com.zhanghao.hefenweathermvp.utils.api;
import com.zhanghao.hefenweathermvp.weatherView.AddCityView;

import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 张浩 on 2016/5/27.
 */
public class LocationPresenter {
    private AddCityView addCityView;
    private Context context;
    public LocationPresenter(AddCityView addCityView,Context context){
        this.addCityView=addCityView;
        this.context=context;
    }
    private String GetCity(double Latitude,double Longitude){
        String url= api.weatherGetCityName(Latitude,Longitude);
        System.out.println(url);
        new MyLocationModel().getLocationJson(url);
        return null;
    }



    public void setUpLocation(){
                addCityView.showDialog("正在定位...");
                new MyLocation().getLocation(context, new MyLocation.LocationResult() {
                    @Override
                    public void gotLocation(final Location location) {
                        if (location==null){
                            addCityView.hideDialog();
                            addCityView.showDialog("定位失败!\n请重试，或者手动输入");
                        }else{
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println(location.getLatitude()+"\n"+location.getLongitude());
                                    GetCity(location.getLatitude(),location.getLongitude());
                                }
                            }).start();
                            addCityView.hideDialog();
                        }
                    }
                });
            }

}

