package com.example.sl.retrofitsample.Impl;

import com.example.sl.retrofitsample.Gson.WeatherBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherApi {
    @GET
    Call<WeatherBean> getAndroidInfo(@Url String url);
}
