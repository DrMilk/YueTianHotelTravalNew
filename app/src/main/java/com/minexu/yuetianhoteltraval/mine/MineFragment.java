package com.minexu.yuetianhoteltraval.mine;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minexu.yuetianhoteltraval.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/3/17.
 */

public class MineFragment extends Fragment implements View.OnClickListener{
    private TextView text_setting;
    private TextView text_personal;
    private TextView text_changepassword;
    private TextView text_collect;
    private TextView text_weather;
    private TextView text_location;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_mine,null);
        text_changepassword= (TextView) view.findViewById(R.id.mine_change);
        text_setting= (TextView) view.findViewById(R.id.mine_setting);
        text_personal= (TextView) view.findViewById(R.id.mine_personal);
        text_weather= (TextView) view.findViewById(R.id.mine_weather);
        text_collect= (TextView) view.findViewById(R.id.mine_collect);
        text_location= (TextView) view.findViewById(R.id.mine_location);
        text_changepassword.setOnClickListener(this);
        text_collect.setOnClickListener(this);
        text_personal.setOnClickListener(this);
        text_setting.setOnClickListener(this);
        text_weather.setOnClickListener(this);
        text_location.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_change:Intent it=new Intent(getActivity(),ChangePasswordActivity.class);startActivity(it);break;
            case R.id.mine_collect:Intent it1=new Intent(getActivity(),CollectActivity.class);startActivity(it1);break;
            case R.id.mine_personal:Intent it2=new Intent(getActivity(),PersonaldActivity.class);startActivity(it2);break;
            case R.id.mine_setting:Intent it3=new Intent(getActivity(),SettingActivity.class);startActivity(it3);break;
            case R.id.mine_weather:Intent it4=new Intent(getActivity(),WeatherActivity.class);startActivity(it4);break;
            case R.id.mine_location:Intent it5=new Intent(getActivity(),WeatherActivity.class);startActivity(it5);break;
        }
    }
}
