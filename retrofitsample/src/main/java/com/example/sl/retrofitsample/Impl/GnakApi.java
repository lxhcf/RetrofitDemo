package com.example.sl.retrofitsample.Impl;

import com.example.sl.retrofitsample.Gson.GankBean;

import retrofit2.Call;
import retrofit2.http.GET;


//http://gank.io/api/data/Android/10/1 这是api接口 正常请求
//get请求
public interface GnakApi {
    @GET("api/data/Android/10/1")
    Call<GankBean> getAndroidInfo();//将官方的ResponseBody改成我们自己定的Gson实体类 这样响应请求以后 给回调方法返回的就是实体类了
}
