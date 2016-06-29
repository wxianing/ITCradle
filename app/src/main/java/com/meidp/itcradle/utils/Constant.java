package com.meidp.itcradle.utils;

import com.meidp.itcradle.MyApplication;

/**
 * Created by John on 2016/6/29.
 */
public class Constant {
    public static final String APPID = "102";
    //    public static final String CODE = SharedPreferencesUtils.getUser(MyApplication.getmInstance()).getCode();
    public static final String CODE = SharedPreferencesUtils.getStringData(MyApplication.getmInstance(), "CODE", null);
}
