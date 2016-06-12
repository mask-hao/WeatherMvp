package com.zhanghao.hefenweathermvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.adapter.WeatherPagerAdapter;
import com.zhanghao.hefenweathermvp.Event.MyEvent;
import com.zhanghao.hefenweathermvp.preferences.PreferencesPresenter;
import com.zhanghao.hefenweathermvp.ui.Fragment.WeatherPagerFragment;
import com.zhanghao.hefenweathermvp.database.DataBasePresenter;
import com.zhanghao.hefenweathermvp.ui.view.MyRefreshLayout;
import com.zhanghao.hefenweathermvp.utils.MyTimeUtils;
import com.zhanghao.hefenweathermvp.utils.comUtils;
import com.zhanghao.hefenweathermvp.weatherPresenter.MainPresenter;
import com.zhanghao.hefenweathermvp.weatherView.MainView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,MainView {
    private MainPresenter mainPresenter;
    private ProgressDialog progressDialog;
    private List<String> NameList;
    private List<String> AddedCityNameList;
    private DataBasePresenter dataBasePresenter;
    private MyRefreshLayout swipeRefreshLayout;
    private TextView toolbarTitle,LastRefreshTime;
    private List<Fragment> fragmentsLists;
    private WeatherPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Init();
        setContentView(R.layout.activity_main);
        initView();
        GoToAdd();
    }

    private void Init() {
        mainPresenter=new MainPresenter(this);
        mainPresenter.initialize(this);
        dataBasePresenter=new DataBasePresenter();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle= (TextView) findViewById(R.id.toolbar_title);
        LastRefreshTime= (TextView) findViewById(R.id.toolbar_title_refreshTime);
        toolbar.setTitle("");
        setTitleName();
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        swipeRefreshLayout= (MyRefreshLayout) findViewById(R.id.RefreshToGetNewData);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorAccent);
        LastRefreshTime.setText("跟新于:"+new MyTimeUtils(this).getLastRefreshTime());
        swipeRefreshLayout.setOnRefreshListener(this);
        fragmentsLists = new ArrayList<>();
        adapter=new WeatherPagerAdapter(getSupportFragmentManager(),fragmentsLists);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                AddedCityNameList=dataBasePresenter.QueryAddedCity();
                toolbarTitle.setText(AddedCityNameList.get(position));
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTitleName() {
        AddedCityNameList=dataBasePresenter.QueryAddedCity();
        if (AddedCityNameList.size()>0) toolbarTitle.setText(AddedCityNameList.get(0));
    }

//    public void showDialog(){
//        progressDialog=new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//    }
//
//    public void DismissDiaLog(){
//        progressDialog.dismiss();
//    }

    //首次跳转到AddcityActivity
    private void GoToAdd() {
        PreferencesPresenter preferencesPresenter = new PreferencesPresenter(this);
        Log.d("count", String.valueOf(preferencesPresenter.QueryCityCount()));
        if (preferencesPresenter.QueryCityCount() <= 0) {
            startActivity(new Intent(this,AddCityActivity.class));
            //startActivityForResult(new Intent(this, AddCityActivity.class),comUtils.REQUEST_ADDCITY);
        }else{
            mainPresenter.setUpViewPager();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getMessage(MyEvent myEvent){
        String name=myEvent.getName();
        if (name!=null){
            Log.d("city","city is Not null");
            NameList=new ArrayList<>();
            NameList.add(name);
            AddOneCityFragment(name);
            /**
             * 第一次加载时候有用
             */
            if (dataBasePresenter.QueryAddedCity().size()==1){
                toolbarTitle.setText(name);
            }
            adapter.notifyDataSetChanged();
        }

    }

    public void AddOneCityFragment(String name){
        Fragment fragment = new WeatherPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cityName", name);
        if (NameList!=null){
            bundle.putString("mode",comUtils.DATA_MODE_SAVE);
        }else{
            bundle.putString("mode",comUtils.DATA_MODE_UPDATE);
        }
        fragment.setArguments(bundle);
        fragmentsLists.add(fragment);
        System.out.println("看看有什么变化:"+fragmentsLists.size());
    }

    @Override
    public void setUpViewPager(List<String> list) {
        System.out.println("-------city-------"+list);
        for (String name : list) {
            AddOneCityFragment(name);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                startActivity(new Intent(this,ManagerCityActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public void onRefresh() {
       final List<String> list=dataBasePresenter.QueryAddedCity();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for (String name:list){
                    mainPresenter.getDataFromHttp(name,comUtils.DATA_MODE_UPDATE);
                    handler.sendEmptyMessage(comUtils.REFRESHED);
                }

            }
        };
        new Thread(runnable).start();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==comUtils.REFRESHED){
                swipeRefreshLayout.setRefreshing(false);
                new MyTimeUtils(MainActivity.this).SaveTimeNow();
                LastRefreshTime.setText("跟新于:"+"刚刚");
                EventBus.getDefault().post(new MyEvent(true));
            }

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
