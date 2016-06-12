package com.zhanghao.hefenweathermvp.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zhanghao.hefenweathermvp.Event.MyEvent;
import com.zhanghao.hefenweathermvp.R;
import com.zhanghao.hefenweathermvp.database.DataBasePresenter;
import com.zhanghao.hefenweathermvp.preferences.PreferencesPresenter;
import com.zhanghao.hefenweathermvp.ui.activity.AddCityActivity;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by 张浩 on 2016/5/29.
 */
public class SearchCityFragment extends Fragment {
    @Bind(R.id.queryCityList)
    ListView queryCityList;
    private DataBasePresenter dataBasePresenter;
    private PreferencesPresenter preferencesPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchcity, container, false);
        ButterKnife.bind(this, view);
        List<Map<String, String>> list = (List<Map<String, String>>) getArguments().getSerializable("searchList");
        init();
        initListView(list);
        return view;
    }

    private void init() {
        dataBasePresenter = new DataBasePresenter();
        preferencesPresenter=new PreferencesPresenter(getContext());
    }

    private void initListView(final List<Map<String,String>> list) {
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),list,
                R.layout.searchcityitem,
                new String[]{"city","prov"},
                new int[]{R.id.search_city, R.id.search_prov});
        queryCityList.setAdapter(simpleAdapter);
        queryCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=list.get(position).get("city");
                preferencesPresenter.SaveCityCount();
                if (!dataBasePresenter.isAddedCity(name)) {
                    dataBasePresenter.SaveAddedCity(name);
                    EventBus.getDefault().post(new MyEvent((name)));
                    EventBus.getDefault().post(new MyEvent("",true,true));
                } else {
                    Toast.makeText( getContext(),"该城市已添加", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
