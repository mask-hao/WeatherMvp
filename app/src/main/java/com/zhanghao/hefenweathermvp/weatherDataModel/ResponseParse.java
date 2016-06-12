package com.zhanghao.hefenweathermvp.weatherDataModel;

import org.json.JSONObject;

/**
 * Created by 张浩 on 2016/5/14.
 */
public interface ResponseParse {
    void ParseJsonInit();
    JSONObject ParseString2Json(String response);
    void ParseJsonData(JSONObject jsonObject,ResponseDetailParse detailParse);
}
