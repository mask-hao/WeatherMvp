package com.zhanghao.hefenweathermvp.weatherPresenter;
import android.content.Context;
import android.util.Log;
import com.zhanghao.hefenweathermvp.ModelBean.GsonWeather;
import com.zhanghao.hefenweathermvp.database.DataBase;
import com.zhanghao.hefenweathermvp.database.DataBaseModel;
import com.zhanghao.hefenweathermvp.database.DataBasePresenter;
import com.zhanghao.hefenweathermvp.utils.api;
import com.zhanghao.hefenweathermvp.utils.comUtils;
import com.zhanghao.hefenweathermvp.weatherDataModel.OnDataRequestListener;
import com.zhanghao.hefenweathermvp.weatherDataModel.RequestData;
import com.zhanghao.hefenweathermvp.weatherDataModel.RequestDataModel;
import com.zhanghao.hefenweathermvp.weatherDataModel.ResponseDetailParse;
import com.zhanghao.hefenweathermvp.weatherDataModel.ResponseParse;
import com.zhanghao.hefenweathermvp.weatherDataModel.ResponseParseModel;
import com.zhanghao.hefenweathermvp.weatherView.MainView;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
/**
 * Created by 张浩 on 2016/5/14.
 */
public class MainPresenter{
    private MainView mainView;
    private RequestData requestData;
    private ResponseParse responseParse;
    private DataBase dataBase;
    private DataBasePresenter dataBasePresenter;
    /**
     *
     * @param mainView
     */
    public MainPresenter(MainView mainView){
        this.mainView=mainView;
        requestData=new RequestDataModel();
        responseParse=new ResponseParseModel();
    }

    public MainPresenter(){
        requestData=new RequestDataModel();
        responseParse=new ResponseParseModel();
    }

    public void initialize(Context context){
        dataBase=new DataBaseModel();
        dataBasePresenter=new DataBasePresenter();
        dataBasePresenter.CopyDataBase(context);
    }

    /**
     * 根据已经添加的城市的信息进行设置viewpager
     */
    public void setUpViewPager(){
        List<String> nameList=dataBasePresenter.QueryAddedCity();
        //System.out.println(nameList);
        mainView.setUpViewPager(nameList);
    }

    /**
     *
     * @param name 城市的名字
     * @param MODE 加载的模式
     */
    public void  getDataFromHttp(String name, final String MODE){
        Map<String,String> map;
        final String cityName;
        dataBase.InitDataBase();
        map=dataBase.QueryCityIdFromDataBase(name);
       // System.out.println("<----name--->"+name);
        //System.out.println("<----Mode--->"+MODE);
        cityName=map.get("city");
        String url=api.weatherApi(map.get("CityId"));
        //Log.d("url",url);
        requestData.InitHttp();
        requestData.RequestWeather(url, new OnDataRequestListener() {
            @Override
            public void OnSuccess(String result) {
                if (result!=null&&!result.isEmpty()){
                    responseParse.ParseJsonInit();
                    JSONObject jsonObject=responseParse.ParseString2Json(result);
                    responseParse.ParseJsonData(jsonObject, new ResponseDetailParse() {
                        @Override
                        public  void getWeatherNow(GsonWeather.WeatherNow weatherNow) {
                                /*
                                *
                                * 判断是否是跟新
                                * 还是第一次保存
                                * 下同
                                * */
                            if (MODE.equals(comUtils.DATA_MODE_SAVE))
                                dataBase.SaveWeatherNow(weatherNow,cityName,comUtils.DATA_MODE_SAVE);
                            else if(MODE.equals(comUtils.DATA_MODE_UPDATE))
                                dataBase.SaveWeatherNow(weatherNow,cityName,comUtils.DATA_MODE_UPDATE);

                        }

                        @Override
                        public  void getForecastDaily(List<GsonWeather.DailyForecast> dailyForecastList) {
                            if (MODE.equals(comUtils.DATA_MODE_SAVE))
                                dataBase.SaveWeatherDaily(dailyForecastList,cityName,comUtils.DATA_MODE_SAVE);
                            else if(MODE.equals(comUtils.DATA_MODE_UPDATE))
                                dataBase.SaveWeatherDaily(dailyForecastList,cityName,comUtils.DATA_MODE_UPDATE);
                        }

                        @Override
                        public  void getForecastHourly(List<GsonWeather.HourlyForecast> hourlyForecasts) {
                            if (MODE.equals(comUtils.DATA_MODE_SAVE))
                                dataBase.SaveWeatherHourly(hourlyForecasts,cityName,comUtils.DATA_MODE_SAVE);
                            else if(MODE.equals(comUtils.DATA_MODE_UPDATE))
                                dataBase.SaveWeatherHourly(hourlyForecasts,cityName,comUtils.DATA_MODE_UPDATE);
                        }

                        @Override
                        public void getErrorMessage(String error) {
                            // TODO: 2016/5/14 我也不知道什么
                        }

                    });
                }
            }
            @Override
            public void Error(String result) {
            }
        });
        System.out.println("--------------------------数据库加载完毕");

    }




}
