package com.meidp.itcradle.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.adapter.InformationAdapter;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBean;
import com.meidp.itcradle.model.Information;
import com.meidp.itcradle.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public MessageFragment() {
    }

    @Override
    public void onInitView() {
        title.setText("资讯中心");
        backImg.setVisibility(View.GONE);
        mDatas = new ArrayList<>();
        mAdapter = new InformationAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().post(getActivity(), Constant.INFORMATIONS_URL, params, new HttpRequestCallBack() {
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
