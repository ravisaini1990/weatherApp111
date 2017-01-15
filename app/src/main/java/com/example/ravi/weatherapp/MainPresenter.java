package com.example.ravi.weatherapp;

import android.util.Log;

import com.example.ravi.weatherapp.model.Example;
import com.example.ravi.weatherapp.networkInterface.WeatherService;
import com.example.ravi.weatherapp.util.NetworkHelper;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ravi on 1/15/2017.
 * Presenter for main activity class
 */

public class MainPresenter {

    private static final String responseMode ="json";
    private static final String APIKEY ="61f3e7021798c19a58187a2e2e2f1f5d";

    private View view;

    MainPresenter(View view) {
        this.view = view;
    }

    /**
     * Get weather json response for city
     * @param cityName
     */
    public void getWeatherDetails(String cityName) {

        Retrofit retrofit = NetworkHelper.getClient();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        weatherService.getWeatherOfCity(cityName , responseMode, APIKEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Example>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("WeatherApp", e.getMessage());
                        view.onError(e);
                    }

                    @Override
                    public final void onNext(Example response) {

                        view.updateUI(response);
                    }
                });

    }


    interface View {
        void updateUI(Example response);
        void onError(Throwable throwable);
    }

}
