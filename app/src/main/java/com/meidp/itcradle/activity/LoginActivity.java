package com.meidp.itcradle.activity;

import android.content.Intent;
import android.view.View;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * A login screen that offers login via email/password.
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @Event(value = {R.id.perfect_information, R.id.login_btn})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.perfect_information:
                intent.setClass(this, PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

