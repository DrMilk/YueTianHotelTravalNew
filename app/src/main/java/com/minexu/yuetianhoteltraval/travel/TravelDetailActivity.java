package com.minexu.yuetianhoteltraval.travel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.Utils.T;
import com.minexu.yuetianhoteltraval.food.RemarkAdapter;
import com.minexu.yuetianhoteltraval.login.LoginActivity;
import com.minexu.yuetianhoteltraval.onlinedata.Remakdata;
import com.minexu.yuetianhoteltraval.onlinedata.Traveldata;
import com.minexu.yuetianhoteltraval.onlinedata.XuUser;

import java.util.ArrayList;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TravelDetailActivity extends Activity{
    private TextView title;
    private TextView context;
    private String name;
    private String id;
    private String title_text;
    private String context_text;
    private ListView listview_remark;
    private ArrayList<String> list_remark_str;
    private ArrayList list_remark;
    private RemarkAdapter foodremarkadapter;
    private Context mcontext;
    private Button button_remark;
    private EditText edit_remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        mcontext=this;
        receivedata();
        initdata();
        initview();
    }

    private void receivedata() {
        list_remark=new ArrayList();
        Bundle bundle=getIntent().getExtras();
        title_text=bundle.getString("title");
        context_text=bundle.getString("context");
        list_remark_str=bundle.getStringArrayList("remarklist");
        id=bundle.getString("id");
        for(int i=0;i<list_remark_str.size();i++){
            BmobQuery<Remakdata> query = new BmobQuery<Remakdata>();
            query.getObject(list_remark_str.get(i), new QueryListener<Remakdata>() {
                @Override
                public void done(Remakdata remakdata, BmobException e) {
                    if(e==null){
                        list_remark.add(remakdata);
                        updataremark();
                    }else {

                    }
                }
            });
        }
    }

    private void updataremark() {
        if(list_remark.size()==list_remark_str.size()){
            foodremarkadapter.notifyDataSetChanged();
        }
    }

    private void initdata() {

    }

    private void initview() {
        foodremarkadapter=new RemarkAdapter(mcontext,list_remark);
        listview_remark= (ListView) findViewById(R.id.travel_detail_list_remark);
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View headview=inflater.inflate(R.layout.detail_head,null);
        View footview=inflater.inflate(R.layout.detail_foot,null);
        title= (TextView) headview.findViewById(R.id.detail_head_title);
        context= (TextView) headview.findViewById(R.id.detail_head_context);
        button_remark= (Button) footview.findViewById(R.id.foot_button);
        edit_remark= (EditText) footview.findViewById(R.id.foot_edittext);
        button_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkuser()){
                    String s=edit_remark.getText().toString().trim();
                    Remakdata remarkdataone=new Remakdata(s,name);
                    list_remark.add(remarkdataone);
                    foodremarkadapter.notifyDataSetChanged();
                    remarkdataone.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                list_remark_str.add(s);
                                Traveldata travekdata=new Traveldata();
                                travekdata.setList_remark(list_remark_str);
                                travekdata.update(id, new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if(e==null){
                                            T.showShot(mcontext,"上传成功！");
                                        }else
                                            T.showShot(mcontext,"上传失败！");
                                    }
                                });
                            }else {
                                T.showShot(mcontext,"上传出错！");
                            }
                        }
                    });
                }
            }
        });
        title.setText(title_text);
        context.setText(context_text);
        listview_remark.setAdapter(foodremarkadapter);
        listview_remark.addHeaderView(headview);
        listview_remark.addFooterView(footview);
    }
    private boolean checkuser() {
        XuUser bmobUser = BmobUser.getCurrentUser(XuUser.class);
        if(bmobUser != null){
            // 允许用户使用应用
             name= (String) BmobUser.getObjectByKey("username");
            return true;
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            userrun();
            return false;
        }
    }

    private void userrun() {
        Intent it=new Intent(TravelDetailActivity.this, LoginActivity.class);
        startActivity(it);
    }
}
