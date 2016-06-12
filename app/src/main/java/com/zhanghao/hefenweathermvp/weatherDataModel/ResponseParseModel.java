package com.zhanghao.hefenweathermvp.weatherDataModel;

import com.google.gson.Gson;
import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 张浩 on 2016/5/14.
 */
public class ResponseParseModel implements ResponseParse {
    private Gson gson;
    @Override
    public void ParseJsonInit() {
        gson=new Gson();
    }
    /**
     * string to jsonObject
     * @param response
     */
    @Override
    public JSONObject ParseString2Json(String response) {
        JSONObject jsonObject=null;
        try {
           jsonObject= new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * parse jsonObject
     * @param jsonObject
     */
    @Override
    public void ParseJsonData(JSONObject jsonObject,ResponseDetailParse detailParse) {
        String objectResult;
        try {
            JSONArray jsonArray=jsonObject.
                    getJSONArray("HeWeather data service 3.0");
            JSONObject object=jsonArray.getJSONObject(0);
            objectResult=object.toString();
            GsonWeather weather=gson.fromJson(objectResult,GsonWeather.class);
            if (weather.getStatus().equals("ok")){
                if (detailParse!=null){
                    detailParse.getWeatherNow(weather.getNow());
                    detailParse.getForecastDaily(weather.getDaily_forecast());
                    detailParse.getForecastHourly(weather.getHourly_forecast());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (detailParse!=null){
                detailParse.getErrorMessage(e.getMessage());
            }
        }

    }
}
