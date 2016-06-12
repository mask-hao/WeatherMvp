package com.zhanghao.hefenweathermvp.Bean;

import org.litepal.crud.DataSupport;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class WeatherNow extends DataSupport {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
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

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    private String code;
    private String txt;
    private String fl;
    private String hum;
    private String pcpn;

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    private String pres;
    private String tmp;
    private String vis;
    private String deg;
    private String dir;
    private String sc;
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
