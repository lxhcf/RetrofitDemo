package com.example.sl.retrofitsample;

//import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.example.sl.retrofitsample.Impl.WeatherApi;
import com.example.sl.retrofitsample.Impl.WeatherApi1;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener, AddressSelector.onSelectorAreaPositionListener {
    private static final String TAG = "MainActivity";
    private BottomDialog dialog;
    RecycleDemoAdapter adapter;
    private List<String> list;

//    RecyclerView recycleView ;

    @BindView(R.id.btn_requet)
    Button mBtnRequet;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.btn_requet1)
    Button mBtnRequet1;
    @BindView(R.id.btn_request2)
    Button mBtnRequest2;
    @BindView(R.id.btn_request3)
    Button mBtnRequest3;
    @BindView(R.id.btn_requet4)
    Button mBtnRequet4;
    @BindView(R.id.btn_request5)
    Button mBtnRequest5;

    @BindView(R.id.recyclerView)
    RecyclerView recycleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mBtnRequet.setOnClickListener(this);
        mBtnRequet1.setOnClickListener(this);
        mBtnRequest2.setOnClickListener(this);
        mBtnRequest3.setOnClickListener(this);
        mBtnRequet4.setOnClickListener(this);
        mBtnRequest5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_requet:
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
                        //我们对返回的GankBean实体类以及response响应数据进行回调函数编写操作
//                        GankBean.ResultsBean bean = response.body().getResults().get(0);

                        List<JavaBean.RowsBean> rows = response.body().getRows();


                        list=new ArrayList<>();

                        for (JavaBean.RowsBean row : rows) {
//                            row.
                            list.add(row.getName());
                        }

                        adapter=new RecycleDemoAdapter(MainActivity.this,list);

                        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);

//                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recycleView.setLayoutManager(layoutManager);
                        recycleView.setAdapter(adapter);
//                        adapter.update(list);

//                        mTvResult.setText(stringBuilder);
                    }

                    @Override
                    public void onFailure(Call<JavaBean> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_requet1:
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://op.juhe.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WeatherApi api1 = retrofit1.create(WeatherApi.class);//创建这次请求的回调接口引用
                Call<WeatherDataBean> call1 = api1.getWeather("4ea58de8a7573377cec0046f5e2469d5");//从这里 调用回调函数的时候 拼接url
                call1.enqueue(new Callback<WeatherDataBean>() {
                    @Override
                    public void onResponse(Call<WeatherDataBean> call, Response<WeatherDataBean> response) {
                        String info = response.body().getResult().getData().getRealtime().getWeather().getInfo();
                        mTvResult.setText("深圳天气:" + info);
                    }

                    @Override
                    public void onFailure(Call<WeatherDataBean> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                break;
            case R.id.btn_request2:
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl("http://gank.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                GnakApi1 api11 = retrofit2.create(GnakApi1.class);//将api变成我们的java接口
                Call<GankBean> call2 = api11.getAndroidInfo1(1);//传入参数 发送请求
                //返回数据 将Gson实体类数据与服务器响应的api数据进行关联 在新建的回调中
                call2.enqueue(new Callback<GankBean>() {
                    @Override
                    public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                        mTvResult.setText(response.body().getResults().get(0).getDesc());
                    }

                    @Override
                    public void onFailure(Call<GankBean> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
                break;
            case R.id.btn_request3:
                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl("http://op.juhe.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                WeatherApi1 api12 = retrofit3.create(WeatherApi1.class);//将api转换成接口的形式 为了完成我们1发送请求以及2从接口返回数据的任务
                Map<String, String> params = new HashMap<>();
                params.put("cityname", "深圳");
                params.put("key", "4ea58de8a7573377cec0046f5e2469d5");
                Call<WeatherDataBean> call3 = api12.getWeatherInfo(params);//完成URL请求的拼接
                call3.enqueue(new Callback<WeatherDataBean>() {//返回的数据回调处理
                    @Override
                    public void onResponse(Call<WeatherDataBean> call, Response<WeatherDataBean> response) {
                        String info = response.body().getResult().getData().getRealtime().getWeather().getInfo();
                        mTvResult.setText("深圳天气:" + info);
                    }

                    @Override
                    public void onFailure(Call<WeatherDataBean> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_requet4:
                Retrofit retrofit4 = new Retrofit.Builder()
                        .baseUrl("http://www.mocky.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PostApi api13 = retrofit4.create(PostApi.class);
                User user = new User();
                user.setId(1);
                user.setName("lgl");

                Call<Result> call4 = api13.postUser(user);//这里 我们上传的是user对象，返回的是JSON数据的result 而后用GSON解析
                call4.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.body().getResult().equals("OK")) {
                            mTvResult.setText(response.body().getResult());
                            Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
                break;
            case R.id.btn_request5:
                Retrofit retrofit5 = new Retrofit.Builder()
                        .baseUrl("http://www.mocky.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PostApi1 api14 = retrofit5.create(PostApi1.class);
                Call<Result> call5=api14.editUser(1,"songlei");
                call5.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.body().getResult().equals("yes")) {
                            mTvResult.setText(response.body().getResult());
                            Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void dialogclose() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void selectorAreaPosition(int provincePosition, int cityPosition, int countyPosition, int streetPosition) {
//        this.provincePosition = provincePosition;
//        this.cityPosition = cityPosition;
//        this.countyPosition = countyPosition;
//        this.streetPosition = streetPosition;
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
//        tv_selector_area.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
