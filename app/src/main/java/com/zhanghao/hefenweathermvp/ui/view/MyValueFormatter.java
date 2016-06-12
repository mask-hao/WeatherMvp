package com.zhanghao.hefenweathermvp.ui.view;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by 张浩 on 2016/6/2.
 */
public class MyValueFormatter implements ValueFormatter {

    private DecimalFormat decimalFormat;
    public MyValueFormatter() {
        decimalFormat = new DecimalFormat("##");
    }


    @Override
    public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
        return decimalFormat.format(v)+"℃";
    }
}
