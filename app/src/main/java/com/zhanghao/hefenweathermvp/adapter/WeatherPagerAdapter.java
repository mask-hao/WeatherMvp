package com.zhanghao.hefenweathermvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> weatherPagerList;
    public WeatherPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.weatherPagerList=list;
        System.out.println("adapterSize："+list.size());
    }

    @Override
    public Fragment getItem(int position) {
        return weatherPagerList.get(position);
    }

    @Override
    public int getCount() {
        return weatherPagerList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

}
