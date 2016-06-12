package com.zhanghao.hefenweathermvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerView;
import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.adapter.CityManagerAdapter;
import com.zhanghao.hefenweathermvp.adapter.ManagerCityAdapter;
import com.zhanghao.hefenweathermvp.database.DataBaseWeatherQueryPresenter;
import com.zhanghao.hefenweathermvp.utils.ToolbarUtils;
import com.zhanghao.hefenweathermvp.weatherView.ManagerCityView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 张浩 on 2016/5/22.
 */
public class ManagerCityActivity extends AppCompatActivity implements ManagerCityView, CityManagerAdapter.ClickListener, DragSelectRecyclerViewAdapter.SelectionListener {
    @Bind(R.id.ManagerCitylist)
    DragSelectRecyclerView ManagerCityList;
    private DataBaseWeatherQueryPresenter dataBaseQueryPresenter;
    private CityManagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managercity);
        ButterKnife.bind(this);
        InitView();
        Init();
        //adapter.restoreInstanceState(savedInstanceState);
    }
    private void InitView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        ToolbarUtils.InitToolbar(this, toolbar, true, true);
        toolbarTitle.setText("城市管理");
        ManagerCityList.setLayoutManager(new GridLayoutManager(this,3));
    }

    private void Init() {
        dataBaseQueryPresenter = new DataBaseWeatherQueryPresenter(this);
        dataBaseQueryPresenter.getWeatherNowList();
    }


    @Override
    public void getWeatherNowList(final List<WeatherNow> weatherNowList) {
        adapter=new CityManagerAdapter(this, weatherNowList,this);
        ManagerCityList.setAdapter(adapter);
        adapter.setSelectionListener(this);
    }

    @Override
    public void OnClick(int position,int size) {

        if (size-1==position){
            startActivity(new Intent(this,AddCityActivity.class));
            finish();
        }else {
            adapter.toggleSelected(position);

        }
    }

    @Override
    public void OnLongClick(int position) {
        ManagerCityList.setDragSelectActive(true,position);
        ManagerCityList.setFingerListener(new DragSelectRecyclerView.FingerListener() {
            @Override
            public void onDragSelectFingerAction(boolean fingerDown) {

            }
        });
    }

    @Override
    public void onDragSelectionChanged(int count) {

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //adapter.saveInstanceState(outState);
    }
}
