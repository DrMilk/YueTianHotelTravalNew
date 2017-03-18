package com.minexu.yuetianhoteltraval.travel;

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
import com.minexu.yuetianhoteltraval.onlinedata.Traveldata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TravelFragment extends Fragment{
    private ListView listView;
    private List<Traveldata> list_data;
    private Context mcontext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_travel,null);
        mcontext=getActivity();
        listView= (ListView) view.findViewById(R.id.activity_travel_listview);
        list_data=new ArrayList<>();
        list_data.add(new Traveldata("上海一日游","上海市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("北京三日游","北京市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("美国七日游","美国市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("泰国自驾游","泰国市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("上海一日游","上海市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("上海一日游","上海市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("上海一日游","上海市区免费接，8点人民广场准时出发",true));
        list_data.add(new Traveldata("上海一日游","上海市区免费接，8点人民广场准时出发",true));
        TravelListAdatapter travelFragment=new TravelListAdatapter(mcontext,list_data);
        listView.setAdapter(travelFragment);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getActivity(),TravelDetailActivity.class);
                startActivity(it);
            }
        });
        return view;
    }
}
