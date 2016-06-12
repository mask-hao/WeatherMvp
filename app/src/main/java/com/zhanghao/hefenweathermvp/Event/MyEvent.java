package com.zhanghao.hefenweathermvp.Event;

/**
 * Created by 张浩 on 2016/5/25.
 */
public class MyEvent {
    public MyEvent(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public boolean isRefreshed() {
        return isRefreshed;
    }

    public void setRefreshed(boolean refreshed) {
        isRefreshed = refreshed;
    }

    private boolean isRefreshed;
    public MyEvent(boolean isRefreshed){
        this.isRefreshed=isRefreshed;
    }

    public boolean isSearchedCityClicked() {
        return SearchedCityClicked;
    }

    public void setSearchedCityClicked(boolean searchedCityClicked) {
        SearchedCityClicked = searchedCityClicked;
    }

    private boolean SearchedCityClicked;

    public MyEvent(String name,boolean isRefreshed,boolean SearchedCityClicked){
        this.SearchedCityClicked=SearchedCityClicked;
    }
}
