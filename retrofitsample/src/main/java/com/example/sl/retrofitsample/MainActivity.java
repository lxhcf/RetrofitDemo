package com.example.sl.retrofitsample;

//import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sl.retrofitsample.Gson.GankBean;
import com.example.sl.retrofitsample.Gson.JavaBean;
import com.example.sl.retrofitsample.Gson.Result;
import com.example.sl.retrofitsample.Gson.User;
import com.example.sl.retrofitsample.Gson.WeatherDataBean;
import com.example.sl.retrofitsample.Impl.GnakApi1;
import com.example.sl.retrofitsample.Impl.JavaApi;
import com.example.sl.retrofitsample.Impl.PostApi;
import com.example.sl.retrofitsample.Impl.PostApi1;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    private BottomDialog dialog;
    RecycleDemoAdapter adapter;
    private List<String> list;

//    RecyclerView recycleView ;


    @BindView(R.id.recyclerView)
    RecyclerView recycleView;
    private ArrayList<String> adcodeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("http://gank.io/")//这里不要用localhost 我们用ip地址来
                .baseUrl("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/")//这里不要用localhost 我们用ip地址来
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        JavaApi api = retrofit.create(JavaApi.class);
        //对发送的请求进行封装 以及对实体类进行指定
        Call<JavaBean> call = api.getAndroidInfo();
        call.enqueue(new Callback<JavaBean>() {//这里回调有子线程 所以可以进行UI操作
            @Override
            public void onResponse(Call<JavaBean> call, Response<JavaBean> response) {
                List<JavaBean.RowsBean> rows = response.body().getRows();
                list=new ArrayList<>();
                adcodeList=new ArrayList<>();
                for (JavaBean.RowsBean row : rows) {
                    list.add(row.getName());
                    adcodeList.add(row.getAdcode());
                }
                adapter=new RecycleDemoAdapter(MainActivity.this,list,0,adcodeList);
                LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
                recycleView.setLayoutManager(layoutManager);
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JavaBean> call, Throwable t) {

            }
        });
    }


}
