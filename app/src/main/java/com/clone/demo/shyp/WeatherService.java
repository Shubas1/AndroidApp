package com.clone.demo.shyp;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {

    @GET("/weather")
    void getWeather(@Query("q") String cityName, Callback<Object> callback);
}