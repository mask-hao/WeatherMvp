package com.zhanghao.hefenweathermvp.ui.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanghao.hefenweathermvp.R;

import java.util.List;

/**
 * Created by 张浩 on 2016/5/28.
 */
public class TestActivity extends AppCompatActivity {
    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        positionTextView= (TextView) findViewById(R.id.position);
        locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList=locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)){
            provider=LocationManager.GPS_PROVIDER;
        }else if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            provider=LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(this,"没有位置提供器!",Toast.LENGTH_SHORT).show();
        }
        Location location=locationManager.getLastKnownLocation(provider);
        if (location!=null){
            showLocation(location);
        }else{
            System.out.println("location is null");
        }
        locationManager.requestLocationUpdates(provider,5000,1,locationListener);
    }

    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void showLocation(Location location) {
        String a=location.getLatitude()+"\n"+location.getLongitude();
        positionTextView.setText(a);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager!=null){
            locationManager.removeUpdates(locationListener);
        }
    }
}
