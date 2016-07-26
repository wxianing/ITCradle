package com.meidp.itcradle.utils;

import com.meidp.itcradle.MyApplication;

/**
 * Created by John on 2016/6/29.
 */
public class Constant {

    public static final String BASE_URL = "http://itcradleapi.meidp.com/";
    public static final String APPID = "102";
    public static final String CODE = SharedPreferencesUtils.getStringData(MyApplication.getmInstance(), "CODE", "");

    public static final String LOGIN_URL = BASE_URL + "systemset/user/login";

    //第三方登录类型
    public static final int QQ = 1;
    public static final int WECHAT = 2;
    public static final int FILE_SELECT_CODE = 1001;
    public static final String ACTION_NEW_VERSION = "com.meten.ifuture.ACTION_NEW_VERSION";
    public static final String VERSION_NAME = "versionName";


    //第三方账号登录
    public static final String LOGIN_BY_THIRD = BASE_URL + "common/user/loginbythird";
    //广告轮播图
    public static final String BANNER_URL = BASE_URL + "article/advertise/getlist";//
    //资讯列表
    public static final String INFORMATIONS_URL = BASE_URL + "article/article/getarticlelist";
    //产品列表
    public static final String PRODUCE_LIST_URL = BASE_URL + "product/home/getproductlist";
    //获取最新版本
    public static final String GET_NEW_VERSION = BASE_URL + "systemset/getlatestversoin";

    public static final String PRODUCE_DETAILS_URL = BASE_URL + "product/home/getproduct";
}
