package com.meidp.itcradle.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;


@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends Activity {

    private SharedPreferences preferences;
    private static String CONFIG = "config";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        // 获得当前窗体对象
        Window window = WelcomeActivity.this.getWindow();
        // 设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences(CONFIG, MODE_PRIVATE);
        new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (preferences.getBoolean("is_first", true)) {
                    preferences.edit().putBoolean("is_first", false).commit();
                    Intent intent = new Intent(WelcomeActivity.this,
                            GuideActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            ;
        }.sendEmptyMessageDelayed(0, 3000);
    }

}
