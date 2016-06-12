package com.zhanghao.hefenweathermvp.ModelBean;

import android.os.Parcel;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/10.
 */
public class GsonWeather {

    private String status;//状态码

    public BaseWeather getBasic() {
        return basic;
    }

    /**
     * 基础数据
     */
    private BaseWeather  basic;
    public static class BaseWeather{
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "BaseWeather{" +
                    "city='" + city + '\'' +
                    ", cnty='" + cnty + '\'' +
                    ", id='" + id + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    '}';
        }

        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        public static class update{
            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }

            private String loc;
            private String utc;
        }
    }


    public List<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 五天的天气预报
     */
    private List<DailyForecast> daily_forecast;
    public static class DailyForecast {
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

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
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

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        private String vis;

        public Astro getAstro() {
            return astro;
        }

        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        /**
         * 跟新时间
         */
        public Astro astro;

        public static class Astro{
            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            private String sr;
            private String ss;
        }

        public WCond getCond() {
            return cond;
        }

        public void setCond(WCond cond) {
            this.cond = cond;
        }

        /**
         * 天气详情
         */
        public WCond cond;

        public static class WCond{
            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }

            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;
        }

        public Tmp getTmp() {
            return tmp;
        }

        public void setTmp(Tmp tmp) {
            this.tmp = tmp;
        }

        /**
         *最高最低温度
         */
        public Tmp tmp;

        public static class Tmp{
            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }

            private String max;
            private String min;
        }

        public Gwind getWind() {
            return wind;
        }

        public void setWind(Gwind wind) {
            this.wind = wind;
        }

        /**
         * 风向
         */
        public Gwind wind;

        public static class Gwind{
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

            private String deg;
            private String dir;
            private String sc;
            private String spd;
        }

    }

    public List<HourlyForecast> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    /**
     * 每小时天气预报
     */
    private List<HourlyForecast> hourly_forecast;
    public static class HourlyForecast {
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

        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;

        public Hwind getWind() {
            return wind;
        }

        public void setWind(Hwind wind) {
            this.wind = wind;
        }

        public Hwind wind;
        public static class Hwind{
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

            private String deg;
            private String dir;
            private String sc;
            private String spd;
        }
    }

    public WeatherNow getNow() {
        return now;
    }

    /**
     * 现在的天气情况
     */

    private WeatherNow now;

    public static class WeatherNow {
        public Cond getCond() {
            return cond;
        }

        public Wind getWind() {
            return wind;
        }

        /**
         * 天气状况
         */
        private Cond cond;

        public static class Cond{
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

            private String code;
            private String txt;
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

        @Override
        public String toString() {
            return "WeatherNow{" +
                    "f1='" + fl + '\'' +
                    ", hum='" + hum + '\'' +
                    ", pcpn='" + pcpn + '\'' +
                    ", tmp='" + tmp + '\'' +
                    ", vis='" + vis + '\'' +
                    '}';
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

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

        private Wind wind;
        public static class Wind{
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
            private String deg;
            private String dir;
            private String sc;
            private String spd;
        }

    }

}
