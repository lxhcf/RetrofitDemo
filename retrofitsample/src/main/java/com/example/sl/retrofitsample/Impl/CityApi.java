package com.example.sl.retrofitsample.Impl;

import com.example.sl.retrofitsample.Gson.CityBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CityApi {
    //    @GET("areas/csv/340000_city.json")
//    Call<CityBean> getAndroidInfo();//将官方的ResponseBody改成我们自己定的Gson实体类 这样响应请求以后 给回调方法返回的就是实体类了
    @GET
    Call<CityBean> getAndroidInfo(@Url String url);
//    public Call<ResponseBody> profilePicture(@Url String url);

}
