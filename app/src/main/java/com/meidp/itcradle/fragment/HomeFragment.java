package com.meidp.itcradle.fragment;


import android.widget.TextView;

import com.meidp.itcradle.R;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.tv)
    private TextView tv;

    private String url = "http://litchiapi.jstv.com/api/GetFeeds?column=7&PageSize=20&pageIndex=1&val=100511D3BE5301280E0992C73A9DEC41";

    public HomeFragment() {
    }

    @Override
    public void onInitData() {
        super.onInitData();
        Map map = new HashMap();
        HttpRequestUtils.getmInstance().Post(getActivity(), url, map, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
//                tv.setText(result);
            }
        });
    }
}
