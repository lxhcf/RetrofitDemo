package com.example.sl.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sl.retrofitsample.Gson.CityBean;
import com.example.sl.retrofitsample.Impl.CityApi;
import com.example.sl.retrofitsample.Impl.DistractApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DistractActivity extends AppCompatActivity {

    RecycleDemoAdapter adapter;
    private List<String> list;
    String adcode;

    @BindView(R.id.distract)
    TextView distract;
    @BindView(R.id.distractRecyclerView)
    RecyclerView recycleView;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distract);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        adcode=intent.getStringExtra("adcode");

        distract.setText(city +"包括以下县:");

        Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://gank.io/")//这里不要用localhost 我们用ip地址来
                .baseUrl("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/")//这里不要用localhost 我们用ip地址来
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        DistractApi api = retrofit.create(DistractApi.class);
//        adcode="340000";
        //对发送的请求进行封装 以及对实体类进行指定
        Call<CityBean> call = api.getAndroidInfo("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/"+adcode+"_district.json");
        call.enqueue(new Callback<CityBean>() {//这里回调有子线程 所以可以进行UI操作
            @Override
            public void onResponse(Call<CityBean> call, Response<CityBean> response) {
                List<CityBean.RowsBean> rows = response.body().getRows();
                list=new ArrayList<>();
                for (CityBean.RowsBean row : rows) {
                    if(row.getParent().equals(city))
                    list.add(row.getName());
                }

                adapter=new RecycleDemoAdapter(DistractActivity.this,list,2,city);

                LinearLayoutManager layoutManager=new LinearLayoutManager(DistractActivity.this);

//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recycleView.setLayoutManager(layoutManager);
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CityBean> call, Throwable t) {

            }
        });

    }
}
