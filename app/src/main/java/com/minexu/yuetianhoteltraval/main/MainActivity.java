package com.minexu.yuetianhoteltraval.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.minexu.yuetianhoteltraval.food.FoodFragment;
import com.minexu.yuetianhoteltraval.hotel.HotelFragment;
import com.minexu.yuetianhoteltraval.mine.MineFragment;
import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.travel.TravelFragment;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private LinearLayout linearLayout_firstab;
    private LinearLayout linearLayout_travel;
    private LinearLayout linearLayout_food;
    private LinearLayout linearLayout_hotel;
    private LinearLayout linearLayout_mine;
    private Fragment fragment_firstab;
    private Fragment fragment_travel;
    private Fragment fragment_food;
    private Fragment fragment_hotel;
    private Fragment fragment_mine;
    private FragmentManager fm;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initView() {
        linearLayout_firstab= (LinearLayout) findViewById(R.id.activity_main_firsttab);
        linearLayout_travel= (LinearLayout) findViewById(R.id.activity_main_travel);
        linearLayout_food= (LinearLayout) findViewById(R.id.activity_main_food);
        linearLayout_hotel= (LinearLayout) findViewById(R.id.activity_main_hotel);
        linearLayout_mine= (LinearLayout) findViewById(R.id.activity_main_mine);
        linearLayout_firstab.setOnClickListener(this);
        linearLayout_food.setOnClickListener(this);
        linearLayout_hotel.setOnClickListener(this);
        linearLayout_mine.setOnClickListener(this);
        linearLayout_travel.setOnClickListener(this);
    }
    private void initFragment() {
        fragment_firstab=new FirstTabFragment();
        fragment_hotel=new HotelFragment();
        fragment_food=new FoodFragment();
        fragment_mine=new MineFragment();
        fragment_travel=new TravelFragment();
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.activity_main_main,fragment_firstab);
        ft.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_firsttab:
                ft=fm.beginTransaction();
                ft.replace(R.id.activity_main_main,fragment_firstab);
                ft.commit();break;
            case R.id.activity_main_travel:
                ft=fm.beginTransaction();
                ft.replace(R.id.activity_main_main,fragment_travel);
                ft.commit();break;
            case R.id.activity_main_food:ft=fm.beginTransaction();
                ft.replace(R.id.activity_main_main,fragment_food);
                ft.commit();break;
            case R.id.activity_main_hotel:
                ft=fm.beginTransaction();
                ft.replace(R.id.activity_main_main,fragment_hotel);
                ft.commit();break;
            case R.id.activity_main_mine:
                ft=fm.beginTransaction();
                ft.replace(R.id.activity_main_main,fragment_mine);
                ft.commit();break;
        }
    }
}
