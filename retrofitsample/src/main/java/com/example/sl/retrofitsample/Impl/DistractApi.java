package com.example.sl.retrofitsample.Impl;

import com.example.sl.retrofitsample.Gson.CityBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DistractApi {
    @GET
    Call<CityBean> getAndroidInfo(@Url String url);
}
