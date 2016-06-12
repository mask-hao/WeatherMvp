package com.zhanghao.hefenweathermvp.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhanghao.hefenweathermvp.R;

/**
 * Created by 张浩 on 2016/5/22.
 */
public class ImageUitls {
    public static void LoadImage(SimpleDraweeView simpleDraweeView,String code){
        //System.out.println(api.weatherImageApi(code));
        Uri uri=Uri.parse(api.weatherImageApi(code));
        simpleDraweeView.setImageURI(uri);
    }
    public static void LoadResImages(Context context,SimpleDraweeView simpleDraweeView){
        Uri uri=Uri.parse("res://com.zhanghao.hefenweathermvp/"+ R.drawable.ic_add_white_36dp);
        simpleDraweeView.getDrawable().setColorFilter(context.getResources().getColor(R.color.colorPrimary),
                PorterDuff.Mode.MULTIPLY);
        simpleDraweeView.setImageURI(uri);
    }

}
