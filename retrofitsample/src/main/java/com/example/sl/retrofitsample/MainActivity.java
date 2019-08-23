package com.example.sl.retrofitsample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sl.retrofitsample.Gson.JavaBean;
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


public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    RecycleDemoAdapter adapter;
    private List<String> list;
    private ArrayList<String> adcodeList;


    @BindView(R.id.recyclerView)
    RecyclerView recycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        JavaApi api = retrofit.create(JavaApi.class);
        //对发送的请求进行封装 以及对实体类进行指定
        Call<JavaBean> call = api.getAndroidInfo();
        call.enqueue(new Callback<JavaBean>() {//这里回调有子线程 可以进行UI操作
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
