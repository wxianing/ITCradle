package com.meidp.itcradle.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.itcradle.R;
import com.meidp.itcradle.activity.ClientManagerActivity;
import com.meidp.itcradle.activity.InventoryManagerActivity;
import com.meidp.itcradle.activity.MessageCenterActivity;
import com.meidp.itcradle.activity.MyCollectActivity;
import com.meidp.itcradle.activity.MyFootprintActivity;
import com.meidp.itcradle.activity.OrderManagerActivity;
import com.meidp.itcradle.activity.PersonalInformationActivity;
import com.meidp.itcradle.activity.ProfitMarginActivity;
import com.meidp.itcradle.activity.RotationRateActivity;
import com.meidp.itcradle.activity.SettingActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_my)
public class MyFragment extends BaseFragment {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    public MyFragment() {
    }

    @Override
    public void onInitView() {
        backImg.setVisibility(View.GONE);
        title.setText("我的");
    }

    @Event(value = {R.id.person_center, R.id.inventory_manager, R.id.order_manager, R.id.client_manager,
            R.id.rotation_rate, R.id.profit_margin, R.id.my_footprint, R.id.my_collect, R.id.message_center,
            R.id.setting})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_center://个人信息
                intent.setClass(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.inventory_manager://库存管理
                intent.setClass(getActivity(), InventoryManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.order_manager://订单管理
                intent.setClass(getActivity(), OrderManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.client_manager://客户管理
                intent.setClass(getActivity(), ClientManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.rotation_rate://我的周转率
                intent.setClass(getActivity(), RotationRateActivity.class);
                startActivity(intent);
                break;
            case R.id.profit_margin://我的利润率
                intent.setClass(getActivity(), ProfitMarginActivity.class);
                startActivity(intent);
                break;
            case R.id.my_footprint://我的足迹
                intent.setClass(getActivity(), MyFootprintActivity.class);
                startActivity(intent);
                break;
            case R.id.my_collect://我的收藏
                intent.setClass(getActivity(), MyCollectActivity.class);
                startActivity(intent);
                break;
            case R.id.message_center://消息中心
                intent.setClass(getActivity(), MessageCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.setting://设置
                intent.setClass(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
