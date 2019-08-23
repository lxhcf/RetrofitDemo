package com.example.sl.retrofitsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sl.retrofitsample.Gson.WeatherBean;
import com.example.sl.retrofitsample.Impl.WeatherApi;
import com.example.sl.retrofitsample.Util.HttpUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    @BindView(R.id.aqi_text)
    TextView aqiText;
    @BindView(R.id.pm25_text)
    TextView pm25Text;

    @BindView(R.id.title_city)
    TextView titleCity;

    @BindView(R.id.degree_text)
    TextView degreeText;

    @BindView(R.id.weather_info_text)
    TextView weatherInfoText;

    @BindView(R.id.title_update_time)
    TextView titleUpdateTime;

    @BindView(R.id.bing_pic_img)
    ImageView bingPicImg;

    @BindView(R.id.forecast_layout)
    LinearLayout forecastLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        loadBingPic();
        Intent intent = getIntent();
        String distract = intent.getStringExtra("distract");
        titleCity.setText(distract);

        /**
         * 请求天气信息
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建网络请求接口的实例
        WeatherApi api = retrofit.create(WeatherApi.class);
        //对发送的请求进行封装 以及对实体类进行指定
        Call<WeatherBean> call = api.getAndroidInfo("http://api.help.bj.cn/apis/weather2d/?id=" + distract);
        call.enqueue(new Callback<WeatherBean>() {//这里回调有子线程 所以可以进行UI操作
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                /**
                 * 标题数据
                 */
                titleUpdateTime.setText(response.body().getWind());
                degreeText.setText(response.body().getTemp());
                weatherInfoText.setText(response.body().getWeather());

                /**
                 * 明日天气数据
                 */
                forecastLayout.removeAllViews();
                View view = LayoutInflater.from(WeatherActivity.this).inflate(R.layout.forecast_item, forecastLayout, false);
                TextView dateText = (TextView) view.findViewById(R.id.date_text);
                TextView infoText = (TextView) view.findViewById(R.id.info_text);
                TextView maxText = (TextView) view.findViewById(R.id.max_text);
                TextView minText = (TextView) view.findViewById(R.id.min_text);
                dateText.setText("明日");
                infoText.setText(response.body().getTomorrow().getTemp());
                maxText.setText(response.body().getTomorrow().getWeather());
                minText.setText(response.body().getTomorrow().getWind());
                forecastLayout.addView(view);

                /**
                 * 空气质量
                 */
                int pm=0;
                try{
                    pm=Integer.parseInt(response.body().getPm25());

                }
                catch (Exception e){

                }
                String aqi="一级";
                switch (pm/50) {
                    case 0:
                        aqi="一级";
                        break;
                    case 1:
                        aqi="二级";
                        break;
                    case 2:
                        aqi="三级";
                        break;
                }
                aqiText.setText(aqi);
                pm25Text.setText(response.body().getPm25());
            }
            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {

            }
        });




    }

    /**
     * 用来从指定网站爬取图片作为天气界面的背景图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String bingPic = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

}
