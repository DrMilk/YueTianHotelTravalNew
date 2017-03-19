package com.minexu.yuetianhoteltraval.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.Utils.T;
import com.minexu.yuetianhoteltraval.main.MainActivity;
import com.minexu.yuetianhoteltraval.onlinedata.XuUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/3/16.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private Button button_ok;
    private Button button_sign;
    private ImageView img_on;
    private boolean button_status=false;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcontext=this;
        initView();
        mbmobinitdata();
    }

    private void initView() {
        button_ok= (Button) findViewById(R.id.button_ok);
        button_sign= (Button) findViewById(R.id.button_sign);
        img_on= (ImageView) findViewById(R.id.login_img_on);
        ImageView img_weibo= (ImageView) findViewById(R.id.login_weibo);
        ImageView img_qq= (ImageView) findViewById(R.id.login_qq);
        ImageView img_weixin= (ImageView) findViewById(R.id.login_weixin);
        img_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_status){
                    img_on.setImageResource(R.mipmap.lottery_fill_order_button_on);
                    button_status=false;
                }else {
                    img_on.setImageResource(R.mipmap.lottery_fill_order_button_off);
                    button_status=true;
                }

            }
        });
        button_ok.setOnClickListener(this);
        button_sign.setOnClickListener(this);
        img_weibo.setOnClickListener(this);
        img_qq.setOnClickListener(this);
        img_weixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ok:
                Intent it=new Intent(LoginActivity.this,MainActivity.class);startActivity(it);break;
            case R.id.button_sign:
                Intent it1=new Intent(LoginActivity.this,SignActivity.class);startActivity(it1);break;
            case R.id.login_weibo:
                T.showShot(mcontext,"-未实现此接口-");break;
            case R.id.login_qq:T.showShot(mcontext,"-未实现此接口-");break;
            case R.id.login_weixin:T.showShot(mcontext,"-未实现此接口-");break;
        }
    }
    private void mbmobinitdata() {
        Bmob.initialize(this, "aa663179451a39705b16ac69692d27f1");
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("aa663179451a39705b16ac69692d27f1")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024*1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }
    private boolean checkuser() {
        XuUser bmobUser = BmobUser.getCurrentUser(XuUser.class);
        if(bmobUser != null){
            // 允许用户使用应用
            //  String name= (String) BmobUser.getObjectByKey("treename");
            //  text_username.setText(name);
            return true;
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            userrun();
            return false;
        }
    }

    private void userrun() {
    }

}
