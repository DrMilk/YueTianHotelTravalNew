package com.minexu.yuetianhoteltraval.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.onlinedata.Fooddata;
import com.minexu.yuetianhoteltraval.onlinedata.Traveldata;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class FoodListAdatapter extends BaseAdapter{
    private List<Fooddata> list_data;
    private MyViewHolder wuViewHolder;
    private LayoutInflater mlayoutinflater;
    public FoodListAdatapter(Context mcontext, List<Fooddata> list_data){
        mlayoutinflater=LayoutInflater.from(mcontext);
        this.list_data=list_data;
    }

    @Override
    public int getCount() {
        return list_data.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            wuViewHolder=new MyViewHolder();
            convertView=mlayoutinflater.inflate(R.layout.listitem_food,null);
            wuViewHolder.text_context= (TextView) convertView.findViewById(R.id.listitem2_context);
            wuViewHolder.text_title= (TextView) convertView.findViewById(R.id.listitem2_title);
            wuViewHolder.img= (ImageView) convertView.findViewById(R.id.listitem2_img);
            wuViewHolder.text_price= (TextView) convertView.findViewById(R.id.listitem2_price);
            convertView.setTag(wuViewHolder);
        }else {
            wuViewHolder= (MyViewHolder) convertView.getTag();
        }
        wuViewHolder.text_title.setText(list_data.get(position).getTitle());
        wuViewHolder.text_context.setText(list_data.get(position).getContext());
        wuViewHolder.text_price.setText(list_data.get(position).getPrice()+"");
        return convertView;
    }
    private class MyViewHolder{
        private TextView text_title;
        private TextView text_context;
        private ImageView img;
        private TextView text_price;
    }
}
