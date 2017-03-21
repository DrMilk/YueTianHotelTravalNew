package com.minexu.yuetianhoteltraval.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.Utils.T;
import com.minexu.yuetianhoteltraval.food.FoodDetailActivity;
import com.minexu.yuetianhoteltraval.login.LoginActivity;
import com.minexu.yuetianhoteltraval.onlinedata.XuUser;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/3/17.
 */

public class SpotDetailActivity extends Activity{
    private Context mcontext;
    private String id;
    private TextView title;
    private TextView context;
    private String title_text;
    private String context_text;
    private Button button_collect;
    private String name;
    private ArrayList<String> list_collect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        mcontext=this;
        receviewdata();
        intiview();
    }

    private void receviewdata() {
        Bundle bundle=getIntent().getExtras();
        title_text=bundle.getString("title");
        context_text=bundle.getString("context");
        id=bundle.getString("id");
    }

    private void intiview() {
        title= (TextView) findViewById(R.id.spot_title);
        context= (TextView) findViewById(R.id.spot_context);
        button_collect= (Button) findViewById(R.id.button_collect);
        button_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkuser()){
                    XuUser xuUser=new XuUser();
                    if(list_collect==null)
                    list_collect=new ArrayList<String>();
                    list_collect.add(id);
                    xuUser.setList_collect(list_collect);
                    xuUser.update(name, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                T.showShot(mcontext,"收藏成功！");
                            }else {
                                T.showShot(mcontext,e.toString());
                            }
                        }
                    });
                }
            }
        });
        title.setText(title_text);
        context.setText(context_text);
    }
    private boolean checkuser() {
        XuUser bmobUser = BmobUser.getCurrentUser(XuUser.class);
        if(bmobUser != null){
            // 允许用户使用应用
            name= (String) BmobUser.getObjectByKey("objectId");
            list_collect= (ArrayList<String>) bmobUser.getList_collect();
            return true;
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            userrun();
            return false;
        }
    }

    private void userrun() {
        Intent it=new Intent(SpotDetailActivity.this, LoginActivity.class);
        startActivity(it);
    }
}
