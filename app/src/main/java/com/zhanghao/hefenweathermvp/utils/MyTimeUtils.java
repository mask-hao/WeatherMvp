package com.zhanghao.hefenweathermvp.utils;

import android.content.Context;
import android.util.Log;

import com.zhanghao.hefenweathermvp.preferences.PreferencesPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张浩 on 2016/5/25.
 */
public class MyTimeUtils {
    private PreferencesPresenter preferencesPresenter;
    private Context context;
    public MyTimeUtils(Context context){
        this.context=context;
        preferencesPresenter=new PreferencesPresenter(context);
    }

    /**
     * 保存现在的
     */
    public void SaveTimeNow(){
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int min =calendar.get(Calendar.MINUTE);
        int date=calendar.get(Calendar.DATE);
        int mon=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        preferencesPresenter.SaveRefreshedTime(year,mon,date,hour,min);
    }
    public   String getLastRefreshTime(){
        Map<String,Integer> map=preferencesPresenter.getLastRefreshTime();
        Calendar calendar=Calendar.getInstance();
        int year=map.get("year");
        int mon=map.get("mon");
        int date=map.get("date");
        int hour=map.get("hour");
        int min=map.get("min");
        int hourNow=calendar.get(Calendar.HOUR_OF_DAY);
        int minNow =calendar.get(Calendar.MINUTE);
        int dateNow=calendar.get(Calendar.DATE);
        int monNow=calendar.get(Calendar.MONTH);
        int yearNow=calendar.get(Calendar.YEAR);

        /**
         * 相减
         */
        int Year_Sub=yearNow-year;
        int Mon_Sub=monNow-mon;
        int date_Sub=dateNow-date;
        int hour_Sub=hourNow-hour;
        int min_Sub=minNow-min;
        String Time_Sub="";

        if (Year_Sub==0){
            if (Mon_Sub==0){
                if (date_Sub==0){
                    if (hour_Sub==0){
                        if (min_Sub==0){
                            Time_Sub="刚刚";
                        }else{
                            Time_Sub=min_Sub+"分钟前";
                        }
                    }else{
                        Time_Sub=hour_Sub+"小时前";
                    }
                }else{
                    Time_Sub=date_Sub+"天前";
                }
            }else{
                Time_Sub=mon+"月"+date+"日";
            }
        }else{
             Time_Sub="一年前";
        }
        return Time_Sub;
    }
    public static String getFormatTime(String time){
//        Date date=null;
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
//        try {
//            date=dateFormat.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return dateFormat.format(date);
//        Matcher matcher= Pattern.compile("HH:mm").matcher(time);
//        if (matcher.find())
//            return matcher.group();
//        else
//            return "30";
        return time.split("\\s")[1];
    }

    public static String getFormatDate(String date){
        String []a=date.split("-");
        return a[1]+"/"+a[2];
    }

    public static String getWeekByDate(String  date){
        String [] weeks={"周日","周一","周二","周三","周四","周五","周六",};
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date Today=null;
        Date now=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateNow=simpleDateFormat.format(now);
        //Log.d("dateNow",dateNow);
        try {
            Today=dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("输入的日期不正确");
        }
        if (date.equals(dateNow)){
            return "今天";
        }else{
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(Today);
            int week_index=calendar.get(Calendar.DAY_OF_WEEK)-1;
            if (week_index<0){
                week_index=0;
            }
            return weeks[week_index];
        }

    }

}
