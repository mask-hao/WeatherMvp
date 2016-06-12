package com.zhanghao.hefenweathermvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhanghao.hefenweathermvp.R;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class HotCityAdapter extends RecyclerView.Adapter<HotCityAdapter.ViewHolder> {
    private List<String> hotCityList;
    public HotCityAdapter(List<String> hotCityList){
        this.hotCityList=hotCityList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void OnClick(View view,int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.hotcity,parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            String city=hotCityList.get(position);
            holder.tv_hotCity.setText(city);
        if (onItemClickListener!=null){
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                   onItemClickListener.OnClick(holder.itemView,pos);
               }
           });
        }
    }

    @Override
    public int getItemCount() {
        return hotCityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_hotCity;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_hotCity= (TextView) itemView.findViewById(R.id.hotCity);
        }
    }
}
