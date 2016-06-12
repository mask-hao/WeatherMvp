package com.zhanghao.hefenweathermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.utils.ImageUitls;
import com.zhanghao.hefenweathermvp.utils.MyTimeUtils;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/26.
 */
public class WeatherDaily_Day_Adapter extends RecyclerView.Adapter<WeatherDaily_Day_Adapter.ViewHolder> {
    private List<WeatherDaily> weatherDailyList;
    public WeatherDaily_Day_Adapter(List<WeatherDaily> list){
        this.weatherDailyList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weatherdailyitem_day,
                parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherDaily weatherDaily=weatherDailyList.get(position);
        String TextDay=weatherDaily.getTxt_d();
        int length=TextDay.length();
        if (length>2){
            TextDay=TextDay.substring((length-2),length);
        }
        holder.DailyTxtD.setText(TextDay);
        ImageUitls.LoadImage(holder.DailyIvD,weatherDaily.getCode_d());
        holder.DailyWeek.setText(MyTimeUtils.getWeekByDate(weatherDaily.getDate()));
        holder.DailyDate.setText(MyTimeUtils.getFormatDate(weatherDaily.getDate()));
    }

    @Override
    public int getItemCount() {
        return weatherDailyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DailyWeek,DailyDate,DailyTxtD;
        SimpleDraweeView DailyIvD;
        public ViewHolder(View itemView) {
            super(itemView);
            DailyWeek= (TextView) itemView.findViewById(R.id.daily_week);
            DailyDate=(TextView)itemView.findViewById(R.id.daily_date);
            DailyTxtD= (TextView) itemView.findViewById(R.id.daily_textD);
            DailyIvD= (SimpleDraweeView) itemView.findViewById(R.id.daily_Iv_D);
        }
    }
}
