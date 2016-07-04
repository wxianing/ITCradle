package com.meidp.itcradle.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.activity.SearchActivity;
import com.meidp.itcradle.adapter.HomeGvAdapter;
import com.meidp.itcradle.adapter.ImagePagerAdapter;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBeans;
import com.meidp.itcradle.model.Banner;
import com.meidp.itcradle.model.HomeRes;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    /**
     * 头部广告
     */
    @ViewInject(R.id.home_banner_viewpager)
    protected AutoScrollViewPager mViewPager;
    @ViewInject(R.id.home_dot_ll)
    protected LinearLayout dotLL;

    private List<Banner> imageUrls;
    private ImagePagerAdapter pagerAdapter;

    @ViewInject(R.id.grid_view)
    private GridView mGridView;
    private List<HomeRes> homeDatas;
    private HomeGvAdapter mGirdViewAdapter;

    public HomeFragment() {
    }

    @Override
    public void onInitView() {
        imageUrls = new ArrayList<>();
        homeDatas = new ArrayList<>();
        homeDatas.add(new HomeRes(R.mipmap.person_activity_area_icon, "广告预约"));
        homeDatas.add(new HomeRes(R.mipmap.person_activity_icon, "数据分析"));
        homeDatas.add(new HomeRes(R.mipmap.person_collect_icon, "财务管理"));
        homeDatas.add(new HomeRes(R.mipmap.person_connect_server, "客户管理"));
        homeDatas.add(new HomeRes(R.mipmap.person_mybase_icon, "培训促销"));
        homeDatas.add(new HomeRes(R.mipmap.person_myshare_icon, "消息推送"));
        homeDatas.add(new HomeRes(R.mipmap.person_mywallet_icon, "选机中心"));
        homeDatas.add(new HomeRes(R.mipmap.person_order_icon, "供应查询"));
        homeDatas.add(new HomeRes(R.mipmap.person_order_icon, "订单查询"));
        mGirdViewAdapter = new HomeGvAdapter(homeDatas, getActivity());
        mGridView.setAdapter(mGirdViewAdapter);
    }

    @Override
    public void onInitData() {
        HttpRequestUtils.getmInstance().post(getActivity(), Constant.BANNER_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<Banner> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<Banner>>() {
                });
                if (null != appBeans && appBeans.getEnumcode() == 0) {
                    imageUrls.addAll(appBeans.getData());
                    pagerAdapter = new ImagePagerAdapter(getActivity(), imageUrls, dotLL);
                    mViewPager.setAdapter(pagerAdapter);
                    mViewPager.setOnPageChangeListener(pagerAdapter);
                    pagerAdapter.refreshData(true);
                }
            }
        });
    }

    @Event(value = R.id.search_btn)
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.search_btn:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.stopAutoScroll();
    }

}
