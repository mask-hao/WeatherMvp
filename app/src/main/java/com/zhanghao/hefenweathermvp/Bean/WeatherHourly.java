package com.zhanghao.hefenweathermvp.Bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class WeatherHourly extends DataSupport {
        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        private String deg;
        private String dir;
        private String sc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    private String spd;


    public String getCityAdded() {
        return cityAdded;
    }

    public void setCityAdded(String cityAdded) {
        this.cityAdded = cityAdded;
    }

    private String cityAdded;

//    public List<CityAdded> getCityAddeds() {
//        return cityAddeds;
//    }
//
//    public void setCityAddeds(List<CityAdded> cityAddeds) {
//        this.cityAddeds = cityAddeds;
//    }
//
//    private List<CityAdded> cityAddeds=new ArrayList<>();
}
