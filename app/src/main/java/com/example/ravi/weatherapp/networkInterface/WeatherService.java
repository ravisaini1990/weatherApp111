package com.example.ravi.weatherapp.networkInterface;

import com.example.ravi.weatherapp.model.Example;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ravi on 1/15/2017.
 * Weather api observables using rxjava
 */

public interface WeatherService {

    @GET("forecast")
    Observable<Example> getWeatherOfCity( @Query("q") String city,
                                          @Query("mode") String responseMode,
                                          @Query("appid") String api);
}
