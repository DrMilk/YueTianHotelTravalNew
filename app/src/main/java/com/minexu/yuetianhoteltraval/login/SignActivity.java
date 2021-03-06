package com.minexu.yuetianhoteltraval.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.Utils.L;
import com.minexu.yuetianhoteltraval.Utils.StringLegalUtil;
import com.minexu.yuetianhoteltraval.Utils.T;
import com.minexu.yuetianhoteltraval.customView.XuProcessDialog;
import com.minexu.yuetianhoteltraval.onlinedata.XuUser;

import java.util.Date;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/3/16.
 */

public class SignActivity extends Activity implements View.OnClickListener{
    private String TAG="SignActivity";
    private EditText edit_phonenum;
    private EditText edit_password;
    private EditText edit_password_again;
    private EditText edit_name;
    private EditText edit_address;
    private EditText edit_qq;
    private EditText edit_wechat;
    private EditText edit_email;
    private TextView text_data;
    private Spinner spinner_sex;
    private Button button_ok;
    private Button button_back;
    private String sex="男";
    private XuProcessDialog xu_dialog;
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mcontext=this;
        initView();
    }

    private void initView() {
        edit_phonenum= (EditText) findViewById(R.id.sign_phonenum);
        edit_password= (EditText) findViewById(R.id.sign_password);
        edit_password_again= (EditText) findViewById(R.id.sign_password_again);
        edit_name= (EditText) findViewById(R.id.sign_name);
        edit_address= (EditText) findViewById(R.id.sign_address);
        edit_qq= (EditText) findViewById(R.id.sign_qq);
        edit_wechat= (EditText) findViewById(R.id.sign_wechat);
        edit_email= (EditText) findViewById(R.id.sign_emial);
        spinner_sex= (Spinner) findViewById(R.id.sign_sex);
        button_ok= (Button) findViewById(R.id.button_ok);
        button_back= (Button) findViewById(R.id.button_back);
        spinner_sex= (Spinner) findViewById(R.id.sign_sex);
        spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex= (String) spinner_sex.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_ok.setOnClickListener(this);
        button_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ok:gosign();break;
            case R.id.button_back:SignActivity.this.finish();break;
        }
    }
    private void gosign() {
        boolean jundge_legal=true;
        button_ok.setEnabled(false);
        String str_phonenum=edit_phonenum.getText().toString().trim();
        String str_password=edit_password.getText().toString().trim();
        String str_password_again=edit_password_again.getText().toString().trim();
        String str_name=edit_name.getText().toString().trim();
        String str_address=edit_address.getText().toString().trim();
        String str_qq=edit_qq.getText().toString().trim();
        String str_wechat=edit_wechat.getText().toString().trim();
        String str_email=edit_email.getText().toString().trim();
        if(!StringLegalUtil.isHaveLength(str_phonenum)){
            edit_phonenum.setError("请输入手机号！");
            jundge_legal=false;
        }
        else if(!StringLegalUtil.isHaveLength(str_password)){
            edit_phonenum.setError("请输入密码！");
            jundge_legal=false;
        }
        else if(!StringLegalUtil.isHaveLength(str_password_again)){
            edit_phonenum.setError("请重复输入密码！");
            jundge_legal=false;
        }
        else if(!StringLegalUtil.isHaveLength(str_name)){
            edit_phonenum.setError("请输入昵称！");
            jundge_legal=false;
        }
        else if(!StringLegalUtil.isHaveLength(str_email)){
            edit_phonenum.setError("请输入邮箱！");
            jundge_legal=false;
        }
        else if(!StringLegalUtil.isCorrectPhonenum(str_phonenum)){
            edit_phonenum.setError("请输入正确的手机号！");
            jundge_legal=false;
        }
        else if(!str_password.equals(str_password_again)){
            edit_password.setError("两次密码输入不一样！");
            edit_password_again.setError("两次密码输入不一样！");
            jundge_legal=false;
        }else if(!StringLegalUtil.isCorrectEmail(str_email)){
            edit_email.setError("请输入正确的邮箱号号！");
            jundge_legal=false;
        }else if(!StringLegalUtil.isCorrectNumer(str_qq)){
            edit_email.setError("请输入正确的QQ号！");
            jundge_legal=false;
        }else if(!StringLegalUtil.isSafePassword(str_password)){
            edit_email.setError("输入密码过于简单！");
            jundge_legal=false;
        }
        if(jundge_legal){
            xu_dialog=new XuProcessDialog(this);xu_dialog.show();
            XuUser xuuser=new XuUser();
            xuuser.setUsername(str_phonenum);
            xuuser.setPassword(str_password);
            xuuser.setPasswordshow(str_password);
            xuuser.setBirthday(new Date(1996,9,14));
            xuuser.setName(str_name);
            xuuser.setAddress(str_address);
            xuuser.signUp(new SaveListener<XuUser>() {
                @Override
                public void done(XuUser o, BmobException e) {
                    if(e==null){
                        T.showShot(mcontext,"注册成功！");
                        L.i(TAG,"注册成功！");
                    }else {
                        T.showShot(mcontext,"注册失败！");
                        L.i(TAG,"注册失败！");
                        button_ok.setEnabled(true);
                    }
                    xu_dialog.dismiss();
                }
            });
        }else {
            button_ok.setEnabled(true);
        }
    }
}
