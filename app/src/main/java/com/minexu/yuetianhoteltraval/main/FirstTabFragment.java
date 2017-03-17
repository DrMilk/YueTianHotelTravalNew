package com.minexu.yuetianhoteltraval.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.onlinedata.Spotdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class FirstTabFragment extends Fragment{
    private ViewPager banner_viewPager;
    private Context mcontext;
    private List<ImageView> list_banner;
    private LinearLayout ll;
    private List<ImageView> list_banner_point;
    private Thread banner_thread;
    private int num_baner=200;
    private ListView listview_spot;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                banner_viewPager.setCurrentItem(num_baner++);
            }
            super.handleMessage(msg);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mcontext=getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_spot,null);
        banner_viewPager= (ViewPager) view.findViewById(R.id.main_banner_viewpager);
        ll= (LinearLayout) view.findViewById(R.id.main_banner_linear_point);
        listview_spot= (ListView) view.findViewById(R.id.activity_spot_listview);
        list_banner=new ArrayList<>();
        ImageView img1=new ImageView(mcontext);
        ImageView img2=new ImageView(mcontext);
        ImageView img3=new ImageView(mcontext);
        ImageView img4=new ImageView(mcontext);
        img1.setImageResource(R.mipmap.qcode_banner_01);
        img2.setImageResource(R.mipmap.qcode_banner_02);
        img3.setImageResource(R.mipmap.qcode_banner_03);
        img4.setImageResource(R.mipmap.qcode_banner_04);
        list_banner.add(img1);
        list_banner.add(img2);
        list_banner.add(img3);
        list_banner.add(img4);
        ArrayList<Spotdata> list_data=new ArrayList<>();
        list_data.add(new Spotdata("故宫","景点介绍 绝大多数第一次来北京的旅游",true));
        list_data.add(new Spotdata("上海迪士尼","欢迎来到一个前所未有的神奇世界",true));
        list_data.add(new Spotdata("广州白水寨","白水寨风景名胜区位于增城区派潭镇",true));
        list_data.add(new Spotdata("测试景点","这里是景点的详细说明在这里有",true));
        SpotListAdatapter spotListAdatapter=new SpotListAdatapter(mcontext,list_data);
        listview_spot.setAdapter(spotListAdatapter);
        listview_spot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent it=new Intent(getActivity(),SpotDetailActivity.class);
                getActivity().startActivity(it);
            }
        });
        XuBannerViewpagerAdapter xubv=new XuBannerViewpagerAdapter(list_banner);
        banner_viewPager.setAdapter(xubv);
        banner_viewPager.setPageTransformer(false,new XuBannerPageTransformer(1));
        banner_viewPager.setCurrentItem(200);
        banner_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                num_baner=position;
                position%=4;
                for(int ii=0;ii<4;ii++){
                    if(ii==position)
                        ll.getChildAt(ii).setBackgroundResource(R.drawable.banner_on_point);
                    else
                        ll.getChildAt(ii).setBackgroundResource(R.drawable.banner_off_point);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        list_banner_point=new ArrayList<>();
        for(int i=0;i<4;i++){
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.rightMargin=6;
            params.leftMargin=6;
            ImageView v = new ImageView(mcontext);
            if(i==0){
                v.setBackgroundResource(R.drawable.banner_on_point);
            }else {
                v.setBackgroundResource(R.drawable.banner_off_point);
            }
            v.setLayoutParams(params);
            ll.addView(v);
        }
        return view;
    }

    @Override
    public void onResume() {
        if(banner_thread==null){
            banner_thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        handler.sendEmptyMessage(1);
                        try {
                            Thread.sleep(4000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            banner_thread.start();
        }
        super.onResume();
    }
}
