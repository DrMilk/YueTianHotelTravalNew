package com.minexu.yuetianhoteltraval.travel;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minexu.yuetianhoteltraval.R;

/**
 * Created by Administrator on 2017/3/17.
 */

public class TravelFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_travel,null);
        return view;
    }
}
