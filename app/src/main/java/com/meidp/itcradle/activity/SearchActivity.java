package com.meidp.itcradle.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewInject(R.id.sales_volume)
    private TextView salesVolume;//销量
    @ViewInject(R.id.price_index)
    private TextView priceIndex;//价格指数
    @ViewInject(R.id.profit_margin)
    private TextView profitMargin;//利润率
    @ViewInject(R.id.click_rate)
    private TextView clickRate;//点击量
    private Resources resources;
    private ColorStateList colorBlack;
    private ColorStateList colorBlue;
    private ColorStateList colorWhite;
    private ColorStateList colorGray;
    @ViewInject(R.id.supplier_text)
    private TextView supplier;//供应商
    @ViewInject(R.id.produce_text)
    private TextView produce;//产品


    @Override
    public void onInitView() {
        resources = getBaseContext().getResources();
        colorBlack = resources.getColorStateList(R.color.textcolor_black);
        colorBlue = resources.getColorStateList(R.color.textcolor_blue);
        colorWhite = resources.getColorStateList(R.color.white);
        colorGray = resources.getColorStateList(R.color.gray_light);

        salesVolume.setTextColor(colorBlue);
        supplier.setBackgroundResource(R.color.white);
    }

    @Event(value = {R.id.back_arrows, R.id.sales_volume, R.id.price_index, R.id.profit_margin, R.id.click_rate, R.id.supplier_text, R.id.produce_text, R.id.check_contacts})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.sales_volume:
                resetTextColor();
                salesVolume.setTextColor(colorBlue);
                break;
            case R.id.price_index:
                resetTextColor();
                priceIndex.setTextColor(colorBlue);
                break;
            case R.id.profit_margin:
                resetTextColor();
                profitMargin.setTextColor(colorBlue);
                break;
            case R.id.click_rate:
                resetTextColor();
                clickRate.setTextColor(colorBlue);
                break;
            case R.id.supplier_text:
                resetBgColor();
                supplier.setBackgroundResource(R.color.white);
                break;
            case R.id.produce_text:
                resetBgColor();
                produce.setBackgroundResource(R.color.white);
                break;
            case R.id.check_contacts:
//                intent.setClass(this,)
                break;
        }
    }

    private void resetTextColor() {
        if (colorBlack != null) {
            salesVolume.setTextColor(colorBlack);
            profitMargin.setTextColor(colorBlack);
            priceIndex.setTextColor(colorBlack);
            clickRate.setTextColor(colorBlack);
        }
    }

    private void resetBgColor() {
        supplier.setBackgroundResource(R.color.gray_light);
        produce.setBackgroundResource(R.color.gray_light);
    }
}
