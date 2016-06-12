package com.zhanghao.hefenweathermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.utils.ImageUitls;
import com.zhanghao.hefenweathermvp.utils.MyTimeUtils;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/26.
 */
public class WeatherHourlyAdapter extends RecyclerView.Adapter<WeatherHourlyAdapter.ViewHolder> {
    private List<WeatherHourly> weatherHourlyList;
    public WeatherHourlyAdapter(List<WeatherHourly> list){
        this.weatherHourlyList=list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.weatherhourlyitem,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherHourly weatherHourly=weatherHourlyList.get(position);
        holder.Tmp.setText(weatherHourly.getTmp()+"℃");
        holder.Time.setText(MyTimeUtils.getFormatTime(weatherHourly.getDate()));
        ImageUitls.LoadImage(holder.simpleDraweeView,"100");
    }

    @Override
    public int getItemCount() {
        return weatherHourlyList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView Tmp,Time;
        SimpleDraweeView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            Tmp= (TextView) itemView.findViewById(R.id.weatherHourly_tmp);
            Time= (TextView) itemView.findViewById(R.id.weatherHourly_time);
            simpleDraweeView= (SimpleDraweeView) itemView.findViewById(R.id.weatherHourly_iv);
        }
    }
}
