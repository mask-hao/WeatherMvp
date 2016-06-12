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
public class WeatherDaily_Night_Adapter extends RecyclerView.Adapter<WeatherDaily_Night_Adapter.ViewHolder> {
    private List<WeatherDaily> weatherDailyList;
    public WeatherDaily_Night_Adapter(List<WeatherDaily> list){
        this.weatherDailyList=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weatherdailyitem_night,
                parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherDaily weatherDaily=weatherDailyList.get(position);
        holder.DailyTxtN.setText(weatherDaily.getTxt_n());
        holder.DailyWind.setText(weatherDaily.getDir()+"\n"+weatherDaily.getSc());
        ImageUitls.LoadImage(holder.DailyIvN,weatherDaily.getCode_n());
    }

    @Override
    public int getItemCount() {
        return weatherDailyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DailyTxtN,DailyWind;
        SimpleDraweeView DailyIvN;
        public ViewHolder(View itemView) {
            super(itemView);
            DailyTxtN= (TextView) itemView.findViewById(R.id.daily_textN);
            DailyWind=(TextView)itemView.findViewById(R.id.daily_Wind);
            DailyIvN= (SimpleDraweeView) itemView.findViewById(R.id.daily_Iv_N);
        }
    }
}
