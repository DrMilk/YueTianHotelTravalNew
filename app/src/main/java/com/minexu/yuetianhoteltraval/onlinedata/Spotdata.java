package com.minexu.yuetianhoteltraval.onlinedata;

/**
 * Created by Administrator on 2017/3/17.
 */

public class Spotdata {
    private String title;
    private String context;
    private boolean pic_status;

    public Spotdata(String title, String context, boolean pic_status) {
        this.title = title;
        this.context = context;
        this.pic_status = pic_status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isPic_status() {
        return pic_status;
    }

    public void setPic_status(boolean pic_status) {
        this.pic_status = pic_status;
    }
}
