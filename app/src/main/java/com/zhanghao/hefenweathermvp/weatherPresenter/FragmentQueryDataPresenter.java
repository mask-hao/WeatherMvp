package com.zhanghao.hefenweathermvp.weatherPresenter;

import android.content.Context;
import android.util.Log;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.database.DataBasePresenter;
import com.zhanghao.hefenweathermvp.database.DataBaseQuery;
import com.zhanghao.hefenweathermvp.database.DataBaseQueryModel;
import com.zhanghao.hefenweathermvp.ui.view.MyValueFormatter;
import com.zhanghao.hefenweathermvp.utils.comUtils;
import com.zhanghao.hefenweathermvp.weatherView.WeatherFragmentView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 张浩 on 2016/5/23.
 */
public class FragmentQueryDataPresenter {
    private DataBaseQuery dataBaseQuery;
    private DataBasePresenter dataBasePresenter;
    private WeatherFragmentView fragmentView;
    private String CityName;
    private Context context;
    public FragmentQueryDataPresenter(WeatherFragmentView weatherFragmentView,Context context,String name){
        this.CityName=name;
        this.fragmentView=weatherFragmentView;
        this.context=context;
        dataBaseQuery=new DataBaseQueryModel();
        dataBaseQuery.initDataBase();
        dataBasePresenter=new DataBasePresenter();

    }

    /**
     * 返回WeatherNowList
     *
     */
    public  void setUpWeatherNow(){
        List<WeatherNow> weatherNowList=dataBaseQuery.QueryWeatherNowList(CityName);
        //System.out.println("看看现在的list的size"+weatherNowList.size());
        fragmentView.setUpWeatherNow(weatherNowList);
    }

    /**
     * 返回WeatherHourlyList
     */
    public void setUpWeatherHourly(){
          List<WeatherHourly> weatherHourlyList=dataBaseQuery.QueryWeatherHourlyList(CityName);
          fragmentView.setUpWeatherHourly(weatherHourlyList);
    }

    /**
     * 返回WeatherDailyList
     */
    public void setUpWeatherDaily(){
        List<WeatherDaily> weatherDailyList=dataBaseQuery.QueryWeatherDailyList(CityName);
        fragmentView.setUpWeatherDaily(weatherDailyList);
    }
    public void setUpWeatherLines(){
        setUpLineData(comUtils.DAY);
        setUpLineData(comUtils.NIGHT);
    }

    private void setUpLineData(int Mode){
        LineData data1=null,data2=null;
        List<WeatherDaily> weatherDailyList=dataBaseQuery.QueryWeatherDailyList(CityName);
        ArrayList<String> arrayListX=new ArrayList<>();
        for (int i=0;i<7;i++){
            arrayListX.add(String.valueOf(i));
        }
        ArrayList<Entry> arrayListYd=new ArrayList<>(),arrayListYn=new ArrayList<>();
        for (int i=0;i<weatherDailyList.size();i++){
            if (Mode== comUtils.DAY){
                int Max= Integer.parseInt(weatherDailyList.get(i).getMax());
                arrayListYd.add(new Entry(Max,i));
                data1=setUpDataSet(arrayListX,arrayListYd,context.getResources().getColor(R.color.colorAccent));
            }else if (Mode==comUtils.NIGHT){
                int Min= Integer.parseInt(weatherDailyList.get(i).getMin());
                arrayListYn.add(new Entry(Min,i));
                data2=setUpDataSet(arrayListX,arrayListYn,context.getResources().getColor(R.color.colorPrimary));
            }
        }
        fragmentView.setUpWeatherTmpLine(data1,data2);
    }


    private LineData setUpDataSet(ArrayList<String> arrayListX,ArrayList<Entry> arrayListY,int color){
        LineDataSet dataSet=new LineDataSet(arrayListY,"Y");
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(6f);
        dataSet.setColor(color);
        dataSet.setCircleColor(color);
        dataSet.setDrawValues(true);
        dataSet.setValueTextSize(12f);
        dataSet.setDrawCubic(true);
        dataSet.setValueFormatter(new MyValueFormatter());
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(dataSet);
        return new LineData(arrayListX, dataSets);
    }


}
