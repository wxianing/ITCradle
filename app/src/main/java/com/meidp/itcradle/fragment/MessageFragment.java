package com.meidp.itcradle.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.adapter.InformationAdapter;
import com.meidp.itcradle.adapter.SpinnerAdapter;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBean;
import com.meidp.itcradle.model.Information;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.fragment_message)
public class MessageFragment extends BaseFragment {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    private int pageIndex = 1;

    @ViewInject(R.id.list_view)
    private ListView mListView;

    private List<Information.DataListBean> mDatas;
    private InformationAdapter mAdapter;

    private List<String> dataList;
    private ArrayAdapter<String> arrayAdapter;
    @ViewInject(R.id.spinner)
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;


    public MessageFragment() {
    }

    @Override
    public void onInitView() {
        title.setText("资讯中心");
        backImg.setVisibility(View.GONE);
        mDatas = new ArrayList<>();
        mAdapter = new InformationAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
        dataList = new ArrayList<>();
        dataList.add("笔记本");
        dataList.add("台式机");
        dataList.add("服务器");

        spinnerAdapter = new SpinnerAdapter(dataList, getActivity());
        spinner.setAdapter(spinnerAdapter);
    }

    @Event(value = R.id.spinner, type = AdapterView.OnItemSelectedListener.class)
    private void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.shows(getActivity(), dataList.get(position));
    }


    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("ChannelId", 1003);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().post(getActivity(), Constant.INFORMATIONS_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppBean<Information> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Information>>() {
                });
                if (null != appBean && appBean.getEnumcode() == 0) {
                    mDatas.addAll(appBean.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
