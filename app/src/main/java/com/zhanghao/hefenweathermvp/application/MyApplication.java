package com.zhanghao.hefenweathermvp.application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.litepal.LitePalApplication;

/**
 * Created by 张浩 on 2016/5/18.
 */
public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init() {
        LitePalApplication.initialize(getApplicationContext());
        Fresco.initialize(getApplicationContext());
    }


}
