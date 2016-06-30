package com.meidp.itcradle.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_message_center)
public class MessageCenterActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInitView() {
        title.setText("消息中心");
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
