package com.zhanghao.hefenweathermvp.ui.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.LineData;
import com.zhanghao.hefenweathermvp.Bean.WeatherDaily;
import com.zhanghao.hefenweathermvp.Bean.WeatherHourly;
import com.zhanghao.hefenweathermvp.Bean.WeatherNow;
import com.zhanghao.hefenweathermvp.Event.MyEvent;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.adapter.WeatherDaily_Day_Adapter;
import com.zhanghao.hefenweathermvp.adapter.WeatherDaily_Night_Adapter;
import com.zhanghao.hefenweathermvp.adapter.WeatherHourlyAdapter;
import com.zhanghao.hefenweathermvp.utils.ImageUitls;
import com.zhanghao.hefenweathermvp.utils.comUtils;
import com.zhanghao.hefenweathermvp.weatherPresenter.FragmentQueryDataPresenter;
import com.zhanghao.hefenweathermvp.weatherPresenter.MainPresenter;
import com.zhanghao.hefenweathermvp.weatherView.WeatherFragmentView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by 张浩 on 2016/5/15.
 */
public class WeatherPagerFragment extends Fragment implements WeatherFragmentView {
    @Bind(R.id.weatherNowImage)
    SimpleDraweeView weatherNowIv;
    @Bind(R.id.WeatherNowTmp)
    TextView WeatherNowTmp;
    @Bind(R.id.WeatherNowHum)
    TextView WeatherNowHum;
    @Bind(R.id.WeatherNowDir)
    TextView WeatherNowDir;
    @Bind(R.id.WeatherNowFl)
    TextView WeatherNowFl;
    @Bind(R.id.weatherHourly_recycler)
    RecyclerView weatherHourlyRecycler;
    @Bind(R.id.WeatherDaily_recycler_D)
    RecyclerView weatherDailyRecyclerD;
    @Bind(R.id.WeatherDaily_recycler_N)
    RecyclerView weatherDailyRecyclerN;
    @Bind(R.id.Tmp_line_D)
    LineChart TmpLineD;
    @Bind(R.id.Tmp_line_N)
    LineChart TmpLineN;
    private FragmentQueryDataPresenter fragmentPresenter;
    private MainPresenter mainPresenter;
    private String name;
    private String mode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weatherpager, container,false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        initData();
        initView();
        return view;
    }

    private void initView() {
        weatherHourlyRecycler.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL));
        weatherDailyRecyclerD.setLayoutManager(new GridLayoutManager(getContext(), 7));
        weatherDailyRecyclerN.setLayoutManager(new GridLayoutManager(getContext(), 7));

    }

    private void init() {
        mainPresenter = new MainPresenter();
        mainPresenter.initialize(getContext());
        name = getArguments().getString("cityName");
        mode = getArguments().getString("mode");
        fragmentPresenter = new FragmentQueryDataPresenter(this, getContext(), name);
    }

    private void initData() {
        if (mode.equals(comUtils.DATA_MODE_UPDATE)) {
            fragmentPresenter.setUpWeatherNow();
            fragmentPresenter.setUpWeatherHourly();
            fragmentPresenter.setUpWeatherDaily();
            fragmentPresenter.setUpWeatherLines();
        } else {
            SaveOneCityWeather(name, mode);
        }
    }

    /**
     * @param AddedName 名字
     * @param Mode      模式
     */
    public void SaveOneCityWeather(final String AddedName, final String Mode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mainPresenter.getDataFromHttp(AddedName, Mode);
                handler.sendEmptyMessage(comUtils.FRAGMENT_LOAD);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == comUtils.FRAGMENT_LOAD) {
                fragmentPresenter.setUpWeatherNow();
                fragmentPresenter.setUpWeatherHourly();
                fragmentPresenter.setUpWeatherDaily();
                fragmentPresenter.setUpWeatherLines();
            }
        }
    };


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getMessage(MyEvent myEvent) {
        if (myEvent.isRefreshed()) {
            SaveOneCityWeather(name, comUtils.DATA_MODE_UPDATE);
        }

    }

    @Override
    public void setUpWeatherNow(List<WeatherNow> weatherNowList) {
        if (weatherNowList.size() > 0) {
            WeatherNow weatherNow = weatherNowList.get(0);
            WeatherNowTmp.setText(weatherNow.getTmp() + "℃" + "\n" + weatherNow.getTxt());
            WeatherNowHum.setText("湿度" + weatherNow.getHum() + "%");
            WeatherNowDir.setText(weatherNow.getDir() + weatherNow.getSc() + "级");
            WeatherNowFl.setText("体感" + weatherNow.getFl() + "℃");
            ImageUitls.LoadImage(weatherNowIv, weatherNow.getCode());
        }

    }

    @Override
    public void setUpWeatherDaily(List<WeatherDaily> weatherDailyList) {
        weatherDailyRecyclerD.setAdapter(new WeatherDaily_Day_Adapter(weatherDailyList));
        weatherDailyRecyclerN.setAdapter(new WeatherDaily_Night_Adapter(weatherDailyList));
    }

    @Override
    public void setUpWeatherHourly(List<WeatherHourly> weatherHourlyList) {
        weatherHourlyRecycler.setAdapter(new WeatherHourlyAdapter(weatherHourlyList));
    }

    @Override
    public void setUpWeatherTmpLine(LineData lineDataD,LineData lineDataN) {
        System.out.println(lineDataD+"\n"+lineDataN);
        setUpLines(TmpLineD,lineDataD);
        setUpLines(TmpLineN,lineDataN);
    }

    public void setUpLines(LineChart line, LineData lineData){
        line.setDrawGridBackground(false);
        line.setTouchEnabled(false);
        line.setDragEnabled(false);
        line.setPinchZoom(false);
        line.setScaleYEnabled(false);
        line.setDescription("");
        Legend legend = line.getLegend();
        legend.setEnabled(false);
        line.setData(lineData);
        line.getAxisLeft().setEnabled(false);
        line.getAxisRight().setEnabled(false);
        line.getXAxis().setEnabled(false);
        line.fitScreen();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
