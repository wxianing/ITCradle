package com.meidp.itcradle.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.meidp.itcradle.R;
import com.meidp.itcradle.utils.NullUtils;

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

    @ViewInject(R.id.supplier_text)
    private TextView supplier;//供应商
    @ViewInject(R.id.produce_text)
    private TextView produce;//产品

    private String type;
    @ViewInject(R.id.search_edittext)
    private EditText searchEdittext;
    private String keyWord;

    /**
     * 初始化
     */
    @Override
    public void onInitView() {
        Intent intent = getIntent();
        type = intent.getStringExtra("TYPE");
        keyWord = intent.getStringExtra("KEYWORD");
        if (NullUtils.isNull(type)) {
            searchEdittext.setText(type + "  " + keyWord);
        } else {
            searchEdittext.setText(keyWord);
        }
        searchEdittext.setSelection(searchEdittext.getText().length());//把光标移到最后
        resources = getBaseContext().getResources();
        colorBlack = resources.getColorStateList(R.color.textcolor_black);
        colorBlue = resources.getColorStateList(R.color.textcolor_blue);

        salesVolume.setTextColor(colorBlue);
        supplier.setBackgroundResource(R.color.white);
    }

    /**
     * 点击事件处理
     *
     * @param v
     */
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
                intent.setClass(this, ContactsActivity.class);
                startActivity(intent);
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
