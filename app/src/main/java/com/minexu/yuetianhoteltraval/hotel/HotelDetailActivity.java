package com.minexu.yuetianhoteltraval.hotel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.food.RemarkAdapter;
import com.minexu.yuetianhoteltraval.onlinedata.Remakdata;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/17.
 */

public class HotelDetailActivity extends Activity{
    private ListView listview_remark;
    private ArrayList list_remark;
    private RemarkAdapter foodremarkadapter;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        mcontext=this;
        initdata();
        initview();
    }
    private void initdata() {
        list_remark=new ArrayList();
        list_remark.add(new Remakdata("这个看起来很好吃的样子","180490289782"));
        list_remark.add(new Remakdata("这个看起来很好吃的样子","181490289782"));
        list_remark.add(new Remakdata("这个看起来很好吃的样子","182490289782"));
        list_remark.add(new Remakdata("这个看起来很好吃的样子","183490289782"));
        list_remark.add(new Remakdata("这个看起来很好吃的样子","184490289782"));
        list_remark.add(new Remakdata("这个看起来很好吃的样子","185490289782"));
    }

    private void initview() {
        foodremarkadapter=new RemarkAdapter(mcontext,list_remark);
        listview_remark= (ListView) findViewById(R.id.hotel_detail_list_remark);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View headview=inflater.inflate(R.layout.detail_head,null);
        View footview=inflater.inflate(R.layout.detail_foot,null);
        listview_remark.setAdapter(foodremarkadapter);
        listview_remark.addHeaderView(headview);
        listview_remark.addFooterView(footview);
    }
}
