package com.meidp.itcradle.model;

/**
 * Created by John on 2016/7/4.
 */
public class HomeRes {

    private int resImg;
    private String resName;

    public HomeRes(int resImg, String resName) {
        this.resImg = resImg;
        this.resName = resName;
    }

    public int getResImg() {
        return resImg;
    }

    public void setResImg(int resImg) {
        this.resImg = resImg;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }
}
