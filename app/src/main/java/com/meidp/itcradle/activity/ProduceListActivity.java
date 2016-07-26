package com.meidp.itcradle.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.adapter.ProduceListAdapter;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBean;
import com.meidp.itcradle.model.Produce;
import com.meidp.itcradle.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_produce_list)
public class ProduceListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private String keyWord = "";
    private int pageIndex = 1;
    @ViewInject(R.id.list_view)
    private ListView mListView;
    private List<Produce.DataListBean> mDatas;
    private ProduceListAdapter mAdapter;
    @ViewInject(R.id.search_edittext)
    private EditText searchEditText;

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


    @Override
    public void onInitView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProduceListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        resources = getBaseContext().getResources();
        colorBlack = resources.getColorStateList(R.color.textcolor_black);
        colorBlue = resources.getColorStateList(R.color.textcolor_blue);
        salesVolume.setTextColor(colorBlue);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        getData(keyWord, pageIndex);
    }

    private void getData(String keyWord, int pageIndex) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().post(ProduceListActivity.this, Constant.PRODUCE_LIST_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppBean<Produce> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Produce>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    mDatas.addAll(appBean.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.search_btn, R.id.sales_volume, R.id.price_index, R.id.profit_margin, R.id.click_rate})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyWord = searchEditText.getText().toString().trim();
                mDatas.clear();
                getData(keyWord, pageIndex);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position).getId();
        Intent intent = new Intent(this, ProduceDetailsActivity.class);
        intent.putExtra("OID", oid);
        startActivity(intent);
    }
}
