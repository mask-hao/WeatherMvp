package com.zhanghao.hefenweathermvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhanghao.hefenweathermvp.Bean.CityContent;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;
import com.zhanghao.hefenweathermvp.utils.comUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张浩 on 2016/5/13.
 */
public class DataBaseModel implements DataBase {

    private DataBaseQuery dataBase;

    public DataBaseModel() {
        dataBase = new DataBaseQueryModel();
    }

    /**
     * @param context 做准备
     */
    @Override
    public void MakeDir(Context context) {
        String path = "data/data/" + context.getPackageName() + "/databases";
        String db = path + "/Weather.db";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File fileDb = new File(db);
        if (!fileDb.exists()) {
            try {
                fileDb.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制城市id数据库
     *
     * @param context 上下文
     */
    @Override
    public void CopyCityDataBase(Context context) {
        String DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        String DB_NAME = "Weather.db";
        String DB_PATHNAME = DB_PATH + DB_NAME;
        File file = new File(DB_PATHNAME);
        if (file.exists()) {
            file.delete();
        }
        try {
            InputStream inputStream = context.getAssets().open("Weather.db");
            OutputStream outputStream = new FileOutputStream(DB_PATHNAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void InitDataBase() {
        SQLiteDatabase sqLiteDatabase = Connector.getWritableDatabase();
    }

    /**
     * @param name 通过城市的名字来获取城市的id代码，
     * @return 通过map返回
     */
    @Override
    public Map<String, String> QueryCityIdFromDataBase(String name) {
        Map<String, String> map = new HashMap<>();
        List<CityContent> cityContentList =
                DataSupport.select("cityid", "prov").where("city=?", name).find(CityContent.class);
        map.put("CityId", cityContentList.get(0).getCityId());
        map.put("prov", cityContentList.get(0).getProv());
        map.put("cnty", cityContentList.get(0).getCnty());
        map.put("city", name);
        return map;
    }

    /**
     * @param Now save weather now
     */
    @Override
    public void SaveWeatherNow(GsonWeather.WeatherNow Now, String cityName, String Mode) {
        WeatherNow weatherNow = new WeatherNow();
        weatherNow.setCode(Now.getCond().getCode());
        weatherNow.setTxt(Now.getCond().getTxt());
        weatherNow.setFl(Now.getFl());
        weatherNow.setTmp(Now.getTmp());
        weatherNow.setHum(Now.getHum());
        weatherNow.setPcpn(Now.getPcpn());
        weatherNow.setPres(Now.getPres());
        weatherNow.setVis(Now.getVis());
        weatherNow.setDeg(Now.getWind().getDeg());
        weatherNow.setDir(Now.getWind().getDir());
        weatherNow.setSpd(Now.getWind().getSpd());
        weatherNow.setSc(Now.getWind().getSc());
        weatherNow.setCityAdded(cityName);
        if (Mode.equals(comUtils.DATA_MODE_SAVE))
            weatherNow.save();
        else if (Mode.equals(comUtils.DATA_MODE_UPDATE))
            weatherNow.updateAll("cityadded=?", cityName);
    }

    /**
     * @param dailyList save weatherDaily
     */
    @Override
    public void SaveWeatherDaily(List<GsonWeather.DailyForecast> dailyList, String cityName, String Mode) {

        for (int i = 0; i < dailyList.size(); i++) {
            WeatherDaily weatherDaily = new WeatherDaily();
            weatherDaily.setSr(dailyList.get(i).getAstro().getSr());
            weatherDaily.setSs(dailyList.get(i).getAstro().getSs());
            weatherDaily.setCode_d(dailyList.get(i).getCond().getCode_d());
            weatherDaily.setCode_n(dailyList.get(i).getCond().getCode_n());
            weatherDaily.setTxt_d(dailyList.get(i).getCond().getTxt_d());
            weatherDaily.setTxt_n(dailyList.get(i).getCond().getTxt_n());
            weatherDaily.setDate(dailyList.get(i).getDate());
            weatherDaily.setHum(dailyList.get(i).getHum());
            weatherDaily.setPcpn(dailyList.get(i).getPcpn());
            weatherDaily.setPop(dailyList.get(i).getPop());
            weatherDaily.setPres(dailyList.get(i).getPres());
            weatherDaily.setVis(dailyList.get(i).getVis());
            weatherDaily.setDeg(dailyList.get(i).getWind().getDeg());
            weatherDaily.setDir(dailyList.get(i).getWind().getDir());
            weatherDaily.setSc(dailyList.get(i).getWind().getSc());
            weatherDaily.setSpd(dailyList.get(i).getWind().getSpd());
            weatherDaily.setMax(dailyList.get(i).getTmp().getMax());
            weatherDaily.setMin(dailyList.get(i).getTmp().getMin());
            weatherDaily.setCityAdded(cityName);
            if (Mode.equals(comUtils.DATA_MODE_SAVE))
                weatherDaily.save();
            else if (Mode.equals(comUtils.DATA_MODE_UPDATE)) {
                int id = dataBase.QueryDaliyCityIds(cityName).get(i).getId();
                weatherDaily.update(id);
            }


        }

    }

    /**
     * @param hourlyForecasts save WeatherHourly
     */
    @Override
    public void SaveWeatherHourly(List<GsonWeather.HourlyForecast> hourlyForecasts, String cityName, String Mode) {

        for (int i = 0; i < hourlyForecasts.size(); i++) {
            WeatherHourly weatherHourly = new WeatherHourly();
            weatherHourly.setDate(hourlyForecasts.get(i).getDate());
            weatherHourly.setHum(hourlyForecasts.get(i).getHum());
            weatherHourly.setPop(hourlyForecasts.get(i).getPop());
            weatherHourly.setPres(hourlyForecasts.get(i).getPres());
            weatherHourly.setTmp(hourlyForecasts.get(i).getTmp());
            weatherHourly.setDeg(hourlyForecasts.get(i).getWind().getDeg());
            weatherHourly.setDir(hourlyForecasts.get(i).getWind().getDir());
            weatherHourly.setSc(hourlyForecasts.get(i).getWind().getSc());
            weatherHourly.setSpd(hourlyForecasts.get(i).getWind().getSpd());
            weatherHourly.setCityAdded(cityName);
            if (Mode.equals(comUtils.DATA_MODE_SAVE))
                weatherHourly.save();
            else if (Mode.equals(comUtils.DATA_MODE_UPDATE)) {
                int id = dataBase.QueryHourlyCityIds(cityName).get(i).getId();
                DataSupport.delete(WeatherHourly.class,id);
                weatherHourly.save();
            }
        }

    }

}
