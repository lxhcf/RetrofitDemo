package com.example.sl.retrofitsample.Impl;

import com.example.sl.retrofitsample.Gson.JavaBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JavaApi {
    @GET("areas/csv/100000_province.json")
    Call<JavaBean> getAndroidInfo();//将官方的ResponseBody改成我们自己定的Gson实体类 这样响应请求以后 给回调方法返回的就是实体类了
}
