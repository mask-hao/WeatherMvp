package com.zhanghao.hefenweathermvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.utils.ImageUitls;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/22.
 */
public class ManagerCityAdapter extends RecyclerView.Adapter<ManagerCityAdapter.ViewHolder> {
    private List<WeatherNow> weatherNowList;
    private Context context;
    public ManagerCityAdapter(Context context, List<WeatherNow> list){
        this.context=context;
        this.weatherNowList=list;
        WeatherNow weatherNow=new WeatherNow();
        weatherNowList.add(weatherNow);
    }
    public OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.managercityitem,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        WeatherNow weatherNow=weatherNowList.get(position);
        if (weatherNow.getTmp()==null){
            ImageUitls.LoadResImages(context,holder.simpleDraweeView);
            holder.textViewTmp.setText("");
        }else{
            ImageUitls.LoadImage(holder.simpleDraweeView,weatherNow.getCode());
            holder.textViewTmp.setText(weatherNow.getTmp()+"℃");
            holder.textViewCityName.setText(weatherNow.getCityAdded());
            }

        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.OnItemClick(holder.itemView,pos);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return weatherNowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textViewTmp,textViewCityName;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView= (SimpleDraweeView) itemView.findViewById(R.id.managerCityImageView);
            textViewTmp= (TextView) itemView.findViewById(R.id.managerCityTem);
            textViewCityName= (TextView) itemView.findViewById(R.id.managerCityName);
        }
    }
}
