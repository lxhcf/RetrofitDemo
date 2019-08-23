package com.example.sl.retrofitsample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * 适配器类继承RecyclerView.Adapter,指定内部类作为泛型
 */
public class RecycleDemoAdapter extends RecyclerView.Adapter<RecycleDemoAdapter.MyHolder> {
    Context context;
    List<String> list;
    int rank;
    List<String> adcodeList;
    String city;

    public RecycleDemoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list=list;
    }
    public RecycleDemoAdapter(Context context, List<String> list,int rank) {
        this.context = context;
        this.list=list;
        this.rank=rank;
    }
    public RecycleDemoAdapter(Context context, List<String> list,int rank,String city) {
        this.context = context;
        this.list=list;
        this.rank=rank;
        this.city=city;

    }
    public RecycleDemoAdapter(Context context, List<String> list,int rank, List<String> adcodeList) {
        this.context = context;
        this.list=list;
        this.rank=rank;
        this.adcodeList=adcodeList;
    }
    public void update( List<String> list){
        this.list=list;
        notifyDataSetChanged();
    }

    /**
     * 创建viewHolder 用来初始化控件
     */
    class MyHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.textView)
        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

    }

    /**
     * 创建viewHolder
     * 注册item
     * 即RecycleView绑定到布局文件
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_demo_item,parent,false);
        MyHolder myHolder=new MyHolder(view);
        return myHolder;

    }

    /**
     * 操作item的地方:为item元素赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        

        String s=list.get(position);

        holder.textView.setText(s);
        //对控件进行监听
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(rank==0){
                    intent=new Intent(context,CityActivity.class);
                    intent.putExtra("province",s);
                    intent.putExtra("adcode",adcodeList.get(position));
                    context.startActivity(intent);
                }
                else if(rank==1) {
                    /**
                     * 注释原因:这里注释掉的部分原来是打算由市跳转县的,
                     *          不过由于网络接口没有提供大部分县的天气数据,
                     *          故不提供县的天气数据,直接跳转天气页面
                     * 优化方案:更换网络接口去获取县的天气数据
                     */
//                    intent=new Intent(context,DistractActivity.class);
//                    intent.putExtra("city",s);
//                    intent.putExtra("adcode",adcodeList.get(position));
//                    context.startActivity(intent);
                    intent=new Intent(context,WeatherActivity.class);
                    intent.putExtra("distract",s);
                    context.startActivity(intent);

                }
                else {
                    intent=new Intent(context,WeatherActivity.class);
                    intent.putExtra("distract",city);
                    context.startActivity(intent);

                }
            }
        });
        //对整个item进行监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 返回item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return list.size();
    }
}
