package com.minexu.yuetianhoteltraval.main;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.minexu.yuetianhoteltraval.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */

public class FirstTabFragment extends Fragment{
    private ViewPager banner_viewPager;
    private Context context;
    private List<ImageView> list_banner;
    private LinearLayout ll;
    private List<ImageView> list_banner_point;
    private Thread banner_thread;
    private int num_baner=200;
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
        context=getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_spot,null);
        banner_viewPager= (ViewPager) view.findViewById(R.id.main_banner_viewpager);
        ll= (LinearLayout) view.findViewById(R.id.main_banner_linear_point);
        list_banner=new ArrayList<>();
        ImageView img1=new ImageView(context);
        ImageView img2=new ImageView(context);
        ImageView img3=new ImageView(context);
        ImageView img4=new ImageView(context);
        img1.setImageResource(R.mipmap.qcode_banner_01);
        img2.setImageResource(R.mipmap.qcode_banner_02);
        img3.setImageResource(R.mipmap.qcode_banner_03);
        img4.setImageResource(R.mipmap.qcode_banner_04);
        list_banner.add(img1);
        list_banner.add(img2);
        list_banner.add(img3);
        list_banner.add(img4);
        XuBannerViewpagerAdapter xubv=new XuBannerViewpagerAdapter(list_banner);
        banner_viewPager.setAdapter(xubv);
        banner_viewPager.setPageTransformer(false,new XuBannerPageTransformer(1));
        banner_viewPager.setCurrentItem(200);
        banner_viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
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
            ImageView v = new ImageView(context);
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
