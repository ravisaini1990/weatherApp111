package com.example.ravi.weatherapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.ravi.weatherapp.model.Example;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.View,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    WeatherAdapter dss;
    ArrayList<com.example.ravi.weatherapp.model.List> list1= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        ListView dd = (ListView) findViewById(R.id.list);
        dss = new WeatherAdapter(this, list1);
        dd.setAdapter(dss);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchWeatherData();
                                    }
                                }
        );

    }


    private  void fetchWeatherData() {
        MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.getWeatherDetails("chennai,india");
    }


    @Override
    public void updateUI(Example example) {
        ArrayList<com.example.ravi.weatherapp.model.List> finalData = (ArrayList<com.example.ravi.weatherapp.model.List>) example.list;
        dss.updateDataSet(finalData);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable throwable) {
        swipeRefreshLayout.setRefreshing(false);
        Log.e("WeatherApp" , "Unexpected err"+ throwable.getMessage());
    }

    @Override
    public void onRefresh() {
        fetchWeatherData();
    }
}
