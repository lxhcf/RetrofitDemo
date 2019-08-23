package com.example.sl.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sl.retrofitsample.Gson.CityBean;
import com.example.sl.retrofitsample.Gson.JavaBean;
import com.example.sl.retrofitsample.Impl.CityApi;
import com.example.sl.retrofitsample.Impl.JavaApi;
import com.smarttop.library.widget.BottomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityActivity extends AppCompatActivity {

    RecycleDemoAdapter adapter;
    private List<String> list;
    String adcode;
    private ArrayList<String> adcodeList;

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.cityRecyclerView)
    RecyclerView recycleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String province = intent.getStringExtra("province");
        adcode=intent.getStringExtra("adcode");
        city.setText(province+"包括以下市:");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        CityApi api = retrofit.create(CityApi.class);
        //对发送的请求进行封装 以及对实体类进行指定
        Call<CityBean> call = api.getAndroidInfo("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/"+adcode+"_city.json");
        call.enqueue(new Callback<CityBean>() {//这里回调有子线程 所以可以进行UI操作
            @Override
            public void onResponse(Call<CityBean> call, Response<CityBean> response) {
                List<CityBean.RowsBean> rows = response.body().getRows();
                list=new ArrayList<>();
                adcodeList=new ArrayList<>();
                for (CityBean.RowsBean row : rows) {
                    list.add(row.getName());
                    adcodeList.add(row.getAdcode());
                }
                adapter=new RecycleDemoAdapter(CityActivity.this,list,1,adcodeList);
                LinearLayoutManager layoutManager=new LinearLayoutManager(CityActivity.this);
                recycleView.setLayoutManager(layoutManager);
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CityBean> call, Throwable t) {

            }
        });

    }
}
