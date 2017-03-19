package com.minexu.yuetianhoteltraval.hotel;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.food.FoodListAdatapter;
import com.minexu.yuetianhoteltraval.onlinedata.Fooddata;
import com.minexu.yuetianhoteltraval.onlinedata.Hoteldata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class HotelFragment extends Fragment{
    private ListView listView;
    private List<Hoteldata> list_hotel;
    private Context mcontext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_hotel,null);
        mcontext=getActivity();
        listView= (ListView) view.findViewById(R.id.activity_hotel_listview);
        list_hotel=new ArrayList<>();
        list_hotel.add(new Hoteldata("上海龙之梦大酒店","行政套房(大床)",true,1959));
        list_hotel.add(new Hoteldata("如家酒店","行政套房(大床)",true,364));
        list_hotel.add(new Hoteldata("万达酒店","行政套房(大床)，8点人民广场准时出发",true,3626));
        list_hotel.add(new Hoteldata("如家酒店","泰国市区免费接，8点人民广场准时出发",true,75));
        list_hotel.add(new Hoteldata("上海龙之梦大酒店","上海市区免费接，8点人民广场准时出发",true,4100));
        list_hotel.add(new Hoteldata("万达酒店","上海市区免费接，8点人民广场准时出发",true,7151));
        list_hotel.add(new Hoteldata("如家酒店","上海市区免费接，8点人民广场准时出发",true,297));
        list_hotel.add(new Hoteldata("如家酒店","上海市区免费接，8点人民广场准时出发",true,2034));
        HotelListAdatapter hotelListAdatapter=new HotelListAdatapter(mcontext,list_hotel);
        listView.setAdapter(hotelListAdatapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getActivity(),HotelDetailActivity.class);
                startActivity(it);
            }
        });
        return view;
    }
}
