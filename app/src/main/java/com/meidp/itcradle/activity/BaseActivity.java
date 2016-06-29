package com.meidp.itcradle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Utility.setTranslucentStatus(this,true);
//        Utility.setActionBar(this);
        x.view().inject(this);
        onInitView();
        onInitData();
        onInitEvent();
    }

    public void onInitView() {
    }

    public void onInitData() {
    }

    public void onInitEvent() {
    }
}