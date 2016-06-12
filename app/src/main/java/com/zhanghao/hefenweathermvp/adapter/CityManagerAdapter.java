package com.zhanghao.hefenweathermvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.utils.ImageUitls;

import java.util.List;

/**
 * Created by 张浩 on 2016/6/2.
 */
public class CityManagerAdapter extends DragSelectRecyclerViewAdapter<CityManagerAdapter.ViewHolder> {
    public interface ClickListener{
        void OnClick(int position,int size);
        void OnLongClick(int position);
    }
    private final  ClickListener mClickListener;
    private Context context;
    private List<WeatherNow> weatherNowList;
    public CityManagerAdapter(Context context,List<WeatherNow> weatherNowList,ClickListener clickListener){
        super();
        this.context=context;
        this.weatherNowList=weatherNowList;
        this.mClickListener=clickListener;
        WeatherNow weatherNow=new WeatherNow();
        weatherNowList.add(weatherNow);
    }

    @Override
    protected boolean isIndexSelectable(int index) {
        return true;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.managercityitem,parent,false));
        return new ViewHolder(view,mClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        WeatherNow weatherNow=weatherNowList.get(position);
        if (weatherNow.getTmp()==null){
            ImageUitls.LoadResImages(context,holder.simpleDraweeView);
            holder.textViewTmp.setText("");
        }else{
            ImageUitls.LoadImage(holder.simpleDraweeView,weatherNow.getCode());
            holder.textViewTmp.setText(weatherNow.getTmp()+"℃");
            holder.textViewCityName.setText(weatherNow.getCityAdded());
        }
    }

    @Override
    public int getItemCount() {
        return weatherNowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        SimpleDraweeView simpleDraweeView;
        TextView textViewTmp,textViewCityName;
        private final ClickListener mClickListener;
        public ViewHolder(View itemView,ClickListener clickListener) {
            super(itemView);
            mClickListener=clickListener;
            simpleDraweeView= (SimpleDraweeView) itemView.findViewById(R.id.managerCityImageView);
            textViewTmp= (TextView) itemView.findViewById(R.id.managerCityTem);
            textViewCityName= (TextView) itemView.findViewById(R.id.managerCityName);
            this.itemView.setOnClickListener(this);
            this.itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener!=null) mClickListener.OnClick(getAdapterPosition(),weatherNowList.size());
        }

        @Override
        public boolean onLongClick(View v) {
            if (mClickListener!=null) mClickListener.OnLongClick(getAdapterPosition());
            return true;
        }
    }
}
