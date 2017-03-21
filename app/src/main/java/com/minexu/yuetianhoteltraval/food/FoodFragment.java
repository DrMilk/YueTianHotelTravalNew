package com.minexu.yuetianhoteltraval.food;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.Utils.L;
import com.minexu.yuetianhoteltraval.onlinedata.Fooddata;
import com.minexu.yuetianhoteltraval.onlinedata.Traveldata;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/3/17.
 */

public class FoodFragment extends Fragment{
    private String TAG="FoodFragment";
    private ListView listView;
    private ArrayList<Fooddata> list_food;
    private Context mcontext;
    private ArrayList<String> list_str;
    private  FoodListAdatapter foodListAdatapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mcontext=getActivity();
        list_str=getArguments().getStringArrayList("data");
        L.i(TAG,list_str.size()+"长度");
        if(list_str==null)
            list_str=new ArrayList<>();
        if(list_food==null){
            list_food=new ArrayList<>();
            for(int i=0;i<list_str.size();i++){
                BmobQuery<Fooddata> query = new BmobQuery<Fooddata>();
                query.getObject(list_str.get(i), new QueryListener<Fooddata>() {
                    @Override
                    public void done(Fooddata object, BmobException e) {
                        if(e==null){
                            list_food.add(object);
                            L.i(TAG,"all"+"下载成功");
                        }else{
                            L.i(TAG,"all"+"下载失败");
                        }
                        updataview();
                    }


                });
            }
        }
        super.onCreate(savedInstanceState);
    }

    private void updataview() {
        if(list_food.size()==list_str.size()){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    L.i(TAG,"Gengxinle");
                    foodListAdatapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_food,null);
        mcontext=getActivity();
        listView= (ListView) view.findViewById(R.id.activity_food_listview);
//        list_food.add(new Fooddata("大闸蟹","金秋时节，又是吃蟹的好日子。而最近",true,200));
//        list_food.add(new Fooddata("水煮肉片","北京市区免费接，8点人民广场准时出发",true,24));
//        list_food.add(new Fooddata("油香儿","美国市区免费接，8点人民广场准时出发",true,26));
//        list_food.add(new Fooddata("宫保鸡丁","泰国市区免费接，8点人民广场准时出发",true,75));
//        list_food.add(new Fooddata("水煮肉片","上海市区免费接，8点人民广场准时出发",true,100));
//        list_food.add(new Fooddata("油香儿","上海市区免费接，8点人民广场准时出发",true,151));
//        list_food.add(new Fooddata("大闸蟹","上海市区免费接，8点人民广场准时出发",true,97));
//        list_food.add(new Fooddata("宫保鸡丁","上海市区免费接，8点人民广场准时出发",true,20));
        foodListAdatapter=new FoodListAdatapter(mcontext,list_food);
        listView.setAdapter(foodListAdatapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(getActivity(),FoodDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",list_food.get(position).getTitle());
                bundle.putString("context",list_food.get(position).getContext());
                bundle.putString("price",list_food.get(position).getPrice()+"");
                bundle.putString("id",list_food.get(position).getObjectId());
                bundle.putStringArrayList("remarklist",list_food.get(position).getList_remarkd());
                it.putExtras(bundle);
                getActivity().startActivity(it);
                L.i(TAG,"点击了吗");
            }
        });
        return view;
    }
}
