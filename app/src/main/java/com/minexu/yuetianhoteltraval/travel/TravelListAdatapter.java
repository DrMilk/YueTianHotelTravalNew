package com.minexu.yuetianhoteltraval.travel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.onlinedata.Spotdata;
import com.minexu.yuetianhoteltraval.onlinedata.Traveldata;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TravelListAdatapter extends BaseAdapter{
    private List<Traveldata> list_data;
    private MyViewHolder wuViewHolder;
    private LayoutInflater mlayoutinflater;
    public TravelListAdatapter(Context mcontext, List<Traveldata> list_data){
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
            convertView=mlayoutinflater.inflate(R.layout.listitem_spot,null);
            wuViewHolder.text_context= (TextView) convertView.findViewById(R.id.listitem_context);
            wuViewHolder.text_title= (TextView) convertView.findViewById(R.id.listitem_title);
            wuViewHolder.img= (ImageView) convertView.findViewById(R.id.listitem_img);
            convertView.setTag(wuViewHolder);
        }else {
            wuViewHolder= (MyViewHolder) convertView.getTag();
        }
        wuViewHolder.text_title.setText(list_data.get(position).getTitle());
        wuViewHolder.text_context.setText(list_data.get(position).getContext());
        return convertView;
    }
    private class MyViewHolder{
        private TextView text_title;
        private TextView text_context;
        private ImageView img;
    }
}
