package com.zhanghao.hefenweathermvp.utils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by 张浩 on 2016/5/22.
 */
public class ToolbarUtils {
    public static void InitToolbar(final AppCompatActivity activity, Toolbar toolBar, boolean HomeButtonEnabled,
                                    boolean HomeAsUpEnabled){
        toolBar.setTitle("");
        activity.setSupportActionBar(toolBar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(HomeAsUpEnabled);
        activity.getSupportActionBar().setHomeButtonEnabled(HomeButtonEnabled);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
