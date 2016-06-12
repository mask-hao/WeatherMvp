package com.zhanghao.hefenweathermvp.database;

import android.content.Context;
import com.zhanghao.hefenweathermvp.Bean.CityAdded;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张浩 on 2016/5/19.
 */
public class DataBasePresenter {
    private DataBaseQuery dataBaseQuery;
    private DataBase dataBase;
    public DataBasePresenter(){
        dataBaseQuery=new DataBaseQueryModel();
        dataBase=new DataBaseModel();
    }
    /**
     *
     * @param context 复制数据库
     */
    public void CopyDataBase(Context context){
        File file=new File("data/data/"+context.getPackageName()+"/databases/");
        if (!file.exists()){
            dataBase.MakeDir(context);
            dataBase.CopyCityDataBase(context);
            dataBase.InitDataBase();
        }
    }

    /**
     *
     * @return 添加的了城市的list
     */
    public List<String> QueryAddedCity(){
        List<String> list=new ArrayList<>();
        List<CityAdded> cityAddedList=dataBaseQuery.QueryAddedCity();
        for (CityAdded cityAdded:cityAddedList){
            list.add(cityAdded.getCityName());
        }
        return list;
    }
    /**
     *
     * @param name 城市的名字
     */
    public void SaveAddedCity(String name){
        dataBaseQuery.AddCity(name);
    }

    /**
     *
     * @param name 城市的名字
     * @return 城市是否添加
     */
    public boolean isAddedCity(String name){
      return dataBaseQuery.isAddedCity(name);
    }


}
