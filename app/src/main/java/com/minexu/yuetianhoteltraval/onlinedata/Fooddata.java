package com.minexu.yuetianhoteltraval.onlinedata;

/**
 * Created by Administrator on 2017/3/17.
 */

public class Fooddata {
    private String title;
    private String context;
    private boolean pic_status;
    private int price;

    public Fooddata(String title, String context, boolean pic_status, int price) {
        this.title = title;
        this.context = context;
        this.pic_status = pic_status;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
