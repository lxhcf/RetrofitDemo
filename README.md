# retrofitsample
用Retrofit请求网络的天气接口
# 效果预览
 ![image](https://github.com/lxhcf/RetrofitDemo/blob/master/Screenshot_2019-08-23-09-44-13-33.png)
 ![image](https://github.com/lxhcf/RetrofitDemo/blob/master/Screenshot_2019-08-23-09-44-25-64.png)

#存在问题:
部分县级区域的天气信息无法获取,故暂时用市的天气代替.
#解决方案
可尝试更换其他免费网络接口,或用阿里收费的天气接口.

####步骤1：添加Retrofit库的依赖
project下的build.gradle：
```
 implementation 'com.squareup.okhttp3:okhttp:3.8.1'
//retrofit基于okhttp进行网络请求，所以这里添加它的依赖，都是square公司的
 implementation 'com.squareup.retrofit2:retrofit:2.1.0'
//retrofit2的库
implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
//配套的JSON解析库
```
同时清单的网络权限
AndroidManifest.xml:
```
<uses-permission android:name="android.permission.INTERNET"/>
```

####步骤2：创建 接收服务器返回JSON数据 的类（实体类/VO类的创建） 

 public static class ResultsBean {
    //服务端返回的数据在前端/客户端对应的实体类
    //下面的实例中我们都选择使用GsonFormat插件直接对JSON数据生成实体类
}

####步骤3：创建 用于描述网络请求 的接口
public interface GnakApi {
    @GET("api/data/Android/10/1")
    Call<GankBean> getAndroidInfo();
//将官方的ResponseBody改成我们自己定的Gson实体类 这样响应请求以后 给回调方法返回的就是实体类了
//之后的实例里，对不同的实例，会使用不同的注解，不同的参数来对应各种不同情况下的请求
}

####步骤4：创建 Retrofit 实例
```
 Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://gank.io/")//这里的url要和接口注解中的url拼接起来 http://gank.io/api/data/Android/10/1
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
```
####步骤5：创建 网络请求接口实例
```
//创建网络请求接口的实例
                GnakApi api = retrofit.create(GnakApi.class);
                //对发送的请求进行封装 以及对实体类进行指定
                Call<GankBean> call = api.getAndroidInfo();
```
####步骤6：发送网络请求（异步）
这里解释一下，这里不用写子线程是因为retrofit是基于okhttp的网络请求库，而okhttp的call回调中有一个namedrunnable是runnable接口的实现类，所以我们回调方法，即enqueue中的内容是在子线程中执行的。
```
call.enqueue(new Callback<GankBean>() {//这里回调有子线程 所以可以进行UI操作
                    @Override
                    public void onResponse(Call<GankBean> call, Response<GankBean> response) {
               //请求成功时的逻辑
                    }

                    @Override
                    public void onFailure(Call<GankBean> call, Throwable t) {
            //请求失败时的逻辑
                    }
                });
```
####步骤7：处理返回数据(我之后的实例都是使用一个textview文本来取出服务器响应并返回的数据)
```
     call.enqueue(new Callback<GankBean>() {//这里回调有子线程 所以可以进行UI操作
                    @Override
                    public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                        //我们对返回的GankBean实体类以及response响应数据进行回调函数编写操作
                        GankBean.ResultsBean bean = response.body().getResults().get(0);
                        //以上代码将实体类bean与返回数据索引为0的数据对应
                        mTvResult.setText(
                                "_id:" + bean.get_id() + "\n" +
                                        "createdAt:" + bean.getCreatedAt() + "\n" +
                                        "desc:" + bean.getDesc() + "\n" +
                                        "images:" + bean.getImages() + "\n" +
                                        "publishedAt:" + bean.getPublishedAt() + "\n" +
                                        "source:" + bean.getSource() + "\n" +
                                        "type:" + bean.getType() + "\n" +
                                        "url:" + bean.getUrl() + "\n" +
                                        "who:" + bean.getWho() + "\n");
                    }

                    @Override
                    public void onFailure(Call<GankBean> call, Throwable t) {

                    }
                });
```









