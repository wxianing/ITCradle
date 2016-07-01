package com.meidp.itcradle.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的库存周转率
 */
@ContentView(R.layout.activity_rotation_rate)
public class RotationRateActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInitView() {
        title.setText("我的库存周转率");
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
