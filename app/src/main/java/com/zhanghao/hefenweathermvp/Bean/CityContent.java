package com.zhanghao.hefenweathermvp.Bean;

import org.litepal.crud.DataSupport;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class CityContent extends DataSupport {
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }


    @Override
    public String toString() {
        return "CityContent{" +
                "city='" + city + '\'' +
                ", cnty='" + cnty + '\'' +
                ", cityId='" + cityId + '\'' +
                ", prov='" + prov + '\'' +
                '}';
    }

    private String city;
    private String cnty;
    private String cityId;
    private String prov;
}
