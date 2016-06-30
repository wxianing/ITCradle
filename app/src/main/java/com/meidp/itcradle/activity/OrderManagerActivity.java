package com.meidp.itcradle.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_order_manager)
public class OrderManagerActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInitView() {
        title.setText("订单管理");
    }

    @Event(value = {R.id.back_arrows, R.id.procurement_order})
    private void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.procurement_order:
                intent.setClass(this, ProcureOrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
