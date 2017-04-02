package com.minexu.yuetianhoteltraval.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.minexu.yuetianhoteltraval.Utils.L;
import com.minexu.yuetianhoteltraval.food.FoodFragment;
import com.minexu.yuetianhoteltraval.hotel.HotelFragment;
import com.minexu.yuetianhoteltraval.mine.MineFragment;
import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.onlinedata.Alldata;
import com.minexu.yuetianhoteltraval.travel.TravelFragment;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private String TAG="MainActivity";
    public Alldata alldata;
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
    private FirstTabFragmentProcess fragment_process;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private int tab_num=0;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
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
        img1= (ImageView) findViewById(R.id.bottom_img1);
        img2= (ImageView) findViewById(R.id.bottom_img2);
        img3= (ImageView) findViewById(R.id.bottom_img3);
        img4= (ImageView) findViewById(R.id.bottom_img4);
        img5= (ImageView) findViewById(R.id.bottom_img5);
    }
    private void initFragment() {
        fragment_process=new FirstTabFragmentProcess();
        BmobQuery<Alldata> query = new BmobQuery<Alldata>();
        query.getObject("7c36329337", new QueryListener<Alldata>() {

            @Override
            public void done(Alldata object, BmobException e) {
                if(e==null){
                    alldata=object;
                    fragment_firstab=new FirstTabFragment();
                    Bundle bundle=new Bundle();
                    bundle.putStringArrayList("data",alldata.getList_spot());
                    fragment_firstab.setArguments(bundle);
                    fragment_hotel=new HotelFragment();
                    bundle=new Bundle();
                    bundle.putStringArrayList("data",alldata.getList_hotel());
                    fragment_hotel.setArguments(bundle);
                    fragment_food=new FoodFragment();
                    bundle=new Bundle();
                    bundle.putStringArrayList("data",alldata.getList_food());
                    fragment_food.setArguments(bundle);
                    fragment_travel=new TravelFragment();
                    bundle=new Bundle();
                    bundle.putStringArrayList("data",alldata.getList_travel());
                    fragment_travel.setArguments(bundle);
                    updataChangeFragment();
                    L.i(TAG,"all"+"下载成功");
                }else{
                    L.i(TAG,"all"+"下载失败");
                }
            }

        });
        fragment_mine=new MineFragment();
        fm=getFragmentManager();
        ft=fm.beginTransaction();
        if(fragment_firstab!=null)
            ft.replace(R.id.activity_main_main,fragment_firstab);
        else
            ft.replace(R.id.activity_main_main,fragment_process);
        ft.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_firsttab:
                initBottomView();
                img1.setImageResource(R.mipmap.host_index_cate_icon_s);

                ft=fm.beginTransaction();
                tab_num=0;
                if(fragment_firstab!=null)
                    ft.replace(R.id.activity_main_main,fragment_firstab);
                else {
                    ft.replace(R.id.activity_main_main,fragment_process);
                   fragment_process.updataTitle("景点列表");
                }

                ft.commit();break;
            case R.id.activity_main_travel:
                initBottomView();
                img2.setImageResource(R.mipmap.host_index_cart_icon_s);
                ft=fm.beginTransaction();
                tab_num=1;
                if(fragment_travel!=null)
                    ft.replace(R.id.activity_main_main,fragment_travel);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("游记");
                }
                ft.commit();break;
            case R.id.activity_main_food:
                initBottomView();
                img3.setImageResource(R.mipmap.host_index_cate_icon_s);
                ft=fm.beginTransaction();
                tab_num=2;
                if(fragment_food!=null)
                    ft.replace(R.id.activity_main_main,fragment_food);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("美食");
                }
                ft.commit();break;
            case R.id.activity_main_hotel:
                initBottomView();
                img4.setImageResource(R.mipmap.host_index_home_icon_s);
                ft=fm.beginTransaction();
                tab_num=3;
                if(fragment_hotel!=null)
                     ft.replace(R.id.activity_main_main,fragment_hotel);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("酒店");
                }
                ft.commit();break;
            case R.id.activity_main_mine:
                initBottomView();
                img5.setImageResource(R.mipmap.host_index_mine_icon_s);
                ft=fm.beginTransaction();
                tab_num=4;
                if(fragment_mine!=null)
                ft.replace(R.id.activity_main_main,fragment_mine);
                else
                    ft.replace(R.id.activity_main_main,fragment_process);
                ft.commit();break;
        }
    }

    private void initBottomView() {
        img2.setImageResource(R.mipmap.host_index_cart_icon);
        img1.setImageResource(R.mipmap.host_index_cate_icon);
        img3.setImageResource(R.mipmap.host_index_cate_icon);
        img4.setImageResource(R.mipmap.host_index_home_icon);
        img5.setImageResource(R.mipmap.host_index_mine_icon);
    }

    public void updataChangeFragment(){
        switch (tab_num){
            case 0:
                ft=fm.beginTransaction();
                tab_num=0;
                if(fragment_firstab!=null)
                    ft.replace(R.id.activity_main_main,fragment_firstab);
                else {
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("景点列表");
                }
                ft.commit();break;
            case 1:
                ft=fm.beginTransaction();
                tab_num=1;
                if(fragment_travel!=null)
                    ft.replace(R.id.activity_main_main,fragment_travel);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("游记");
                }
                ft.commit();break;
            case 2:
                tab_num=2;
                if(fragment_food!=null)
                    ft.replace(R.id.activity_main_main,fragment_food);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("美食");
                }
                ft.commit();break;
            case 3:
                ft=fm.beginTransaction();
                tab_num=3;
                if(fragment_hotel!=null)
                    ft.replace(R.id.activity_main_main,fragment_hotel);
                else{
                    ft.replace(R.id.activity_main_main,fragment_process);
                    fragment_process.updataTitle("酒店");
                }
                ft.commit();break;
            case 4:
                ft=fm.beginTransaction();
                tab_num=4;
                if(fragment_mine!=null)
                    ft.replace(R.id.activity_main_main,fragment_mine);
                else
                    ft.replace(R.id.activity_main_main,fragment_process);
                ft.commit();break;
        }
    }
}
