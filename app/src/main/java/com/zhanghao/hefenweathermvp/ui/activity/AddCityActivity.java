package com.zhanghao.hefenweathermvp.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zhanghao.hefenweathermvp.Event.MyEvent;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.adapter.HotCityAdapter;
import com.zhanghao.hefenweathermvp.database.DataBasePresenter;
import com.zhanghao.hefenweathermvp.database.DataBaseWeatherQueryPresenter;
import com.zhanghao.hefenweathermvp.preferences.PreferencesPresenter;
import com.zhanghao.hefenweathermvp.ui.Fragment.SearchCityFragment;
import com.zhanghao.hefenweathermvp.utils.PermissionUtil;
import com.zhanghao.hefenweathermvp.utils.ToolbarUtils;
import com.zhanghao.hefenweathermvp.utils.comUtils;
import com.zhanghao.hefenweathermvp.weatherPresenter.AddCityPresenter;
import com.zhanghao.hefenweathermvp.weatherPresenter.LocationPresenter;
import com.zhanghao.hefenweathermvp.weatherView.AddCityView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 张浩 on 2016/5/14.
 */
public class AddCityActivity extends AppCompatActivity implements AddCityView{
    @Bind(R.id.addCity_view)
    LinearLayout addCityView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.hotCityRecycler)
    RecyclerView hotCityRecycler;
    @Bind(R.id.searchResult)
    FrameLayout searchResultFrame;
    private SearchCityFragment searchCityFragment;
    private AddCityPresenter cityPresenter;
    private LocationPresenter locationPresenter;
    private PreferencesPresenter preferencesPresenter;
    private DataBasePresenter dataBasePresenter;
    private DataBaseWeatherQueryPresenter dataBaseWeatherQueryPresenter;
    private ProgressDialog dialog;
    private String[] PERMISSION_CONTACTS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcity);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initView();
    }

    private void init() {
        cityPresenter = new AddCityPresenter(this);
        preferencesPresenter = new PreferencesPresenter(this);
        dataBasePresenter = new DataBasePresenter();
        locationPresenter=new LocationPresenter(this,this);
        dataBaseWeatherQueryPresenter = new DataBaseWeatherQueryPresenter(this);
    }

    private void initView() {
        ToolbarUtils.InitToolbar(AddCityActivity.this, toolbar, false, false);
        hotCityRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        cityPresenter.setHotCityData();
        dialog=new ProgressDialog(this);
        //locationPresenter.setUpLocation();


    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showDialog(String str) {
        dialog.setMessage(str);
        dialog.setCancelable(false);
        dialog.show();
    }


    /**
     * 初始化权限
     */
    private void intiPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            RequestPermission();
        } else {
            Toast.makeText(this, "权限已经获得，正在定位", Toast.LENGTH_SHORT).show();
//            locationPresenter.InitLocation();
        }
    }

    /**
     * 申请权限
     */
    private void RequestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Snackbar.make(addCityView, "缺少必要的权限！请点击设置",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("设置", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(AddCityActivity.this, PERMISSION_CONTACTS,
                                            comUtils.REQUEST_LOCATION);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSION_CONTACTS, comUtils.REQUEST_LOCATION);
        }
    }

    /**
     * 权限的申请的回调
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == comUtils.REQUEST_LOCATION) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                //TODO 开始定位
                Toast.makeText(this, "权限已经获得，正在定位", Toast.LENGTH_SHORT).show();
                //System.out.println();
//                locationPresenter.InitLocation();
            } else {
                Snackbar.make(addCityView, "缺少必要的权限！请点击设置",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("设置", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat
                                        .requestPermissions(AddCityActivity.this, PERMISSION_CONTACTS,
                                                comUtils.REQUEST_LOCATION);
                            }
                        }).show();
            }
        }
    }

    @Override
    public void setHotCity(final List<String> HotCityList) {
        HotCityAdapter adapter;
        adapter = new HotCityAdapter(HotCityList);
        hotCityRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                String name = HotCityList.get(position);
                preferencesPresenter.SaveCityCount();
                if (!dataBasePresenter.isAddedCity(name)) {
                    dataBasePresenter.SaveAddedCity(name);
                    EventBus.getDefault().post(new MyEvent((name)));
                    AddCityActivity.this.finish();
                } else {
                    Toast.makeText(AddCityActivity.this, "该城市已添加", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 搜索结果的回调
     * @param list 返回的是所查询的城市的List<Map<String,String>>
     */
    @Override
    public void setSearchResult(final List<Map<String, String>> list) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        searchCityFragment=new SearchCityFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("searchList", (Serializable) list);
        searchCityFragment.setArguments(bundle);
        transaction.replace(R.id.searchResult,searchCityFragment);
        searchResultFrame.setVisibility(View.VISIBLE);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        initSearchView(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 初始化
     * @param searchView 搜索框
     */
    private void initSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dataBaseWeatherQueryPresenter.getSearchCityName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (searchCityFragment!=null){
                    getSupportFragmentManager().beginTransaction().remove(searchCityFragment).commit();
                    searchResultFrame.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getMessage(MyEvent myEvent){
        if (myEvent.isSearchedCityClicked()){
            getSupportFragmentManager().beginTransaction().remove(searchCityFragment).commit();
            searchResultFrame.setVisibility(View.GONE);
            AddCityActivity.this.finish();
        }
    }
}
