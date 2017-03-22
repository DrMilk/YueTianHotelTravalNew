package com.minexu.yuetianhoteltraval.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.login.LoginActivity;
import com.minexu.yuetianhoteltraval.onlinedata.XuUser;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/3/18.
 */

public class PersonaldActivity extends Activity{
    private BmobUser xuuser;
    private String phonenum;
    private String address;
    private String qq;
    private String wechat;
    private String email;
    private String sex;
    private EditText ed_qq;
    private EditText ed_wechat;
    private EditText ed_emial;
    private EditText ed_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initview();
    }

    private void initview() {
        ed_qq= (EditText) findViewById(R.id.personal_qq);
        ed_wechat= (EditText) findViewById(R.id.personal_weixin);
        ed_emial= (EditText) findViewById(R.id.personal_email);
        ed_address= (EditText) findViewById(R.id.personal_address);
    }

    @Override
    protected void onResume() {
        if(checkuser()){

        }
        super.onResume();
    }
    private boolean checkuser() {
        XuUser bmobUser = BmobUser.getCurrentUser(XuUser.class);
        if(bmobUser != null){
            // 允许用户使用应用
            xuuser=  BmobUser.getCurrentUser();
            qq= (String) BmobUser.getObjectByKey("qq");
            wechat= (String) BmobUser.getObjectByKey("wechat");
            address=(String) BmobUser.getObjectByKey("address");
            sex=(String) BmobUser.getObjectByKey("sex");
            //  text_username.setText(name);
            return true;
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            Intent it=new Intent(PersonaldActivity.this, LoginActivity.class);
            startActivity(it);
            PersonaldActivity.this.finish();
            return false;
        }
    }
}
