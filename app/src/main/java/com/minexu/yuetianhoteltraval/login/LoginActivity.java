package com.minexu.yuetianhoteltraval.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.minexu.yuetianhoteltraval.R;
import com.minexu.yuetianhoteltraval.main.MainActivity;

/**
 * Created by Administrator on 2017/3/16.
 */

public class LoginActivity extends Activity implements View.OnClickListener{
    private Button button_ok;
    private Button button_sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        button_ok= (Button) findViewById(R.id.button_ok);
        button_sign= (Button) findViewById(R.id.button_sign);
        button_ok.setOnClickListener(this);
        button_sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_ok:
                Intent it=new Intent(LoginActivity.this,MainActivity.class);startActivity(it);break;
            case R.id.button_sign:
                Intent it1=new Intent(LoginActivity.this,SignActivity.class);startActivity(it1);break;
        }
    }
}
