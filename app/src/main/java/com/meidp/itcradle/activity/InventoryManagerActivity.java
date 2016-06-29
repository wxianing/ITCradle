package com.meidp.itcradle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

//库存管理
@ContentView(R.layout.activity_inventory_manager)
public class InventoryManagerActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onInitView() {
        title.setText("库存管理");
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
