package com.meidp.itcradle.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;
import com.meidp.itcradle.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_setting)
public class SettingActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInitView() {
        title.setText("设置");
    }

    @Event(value = {R.id.back_arrows, R.id.logout})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.logout://退出登录
                if (MainActivity.mainActivity != null || LoginActivity.activity != null) {
                    MainActivity.mainActivity.finish();
                    MainActivity.mainActivity = null;
                    LoginActivity.activity.finish();
                    LoginActivity.activity = null;
                    Intent intent = new Intent();
                    SharedPreferencesUtils.setLoginTag(this, false);
                    intent.setClass(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}
