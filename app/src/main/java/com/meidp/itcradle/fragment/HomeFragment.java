package com.meidp.itcradle.fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.activity.ClientManagerActivity;
import com.meidp.itcradle.activity.MessageCenterActivity;
import com.meidp.itcradle.activity.OrderManagerActivity;
import com.meidp.itcradle.activity.ProduceListActivity;
import com.meidp.itcradle.activity.SearchActivity;
import com.meidp.itcradle.adapter.HomeGvAdapter;
import com.meidp.itcradle.adapter.ImagePagerAdapter;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBeans;
import com.meidp.itcradle.model.Banner;
import com.meidp.itcradle.model.HomeRes;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.ToastUtils;
import com.meidp.itcradle.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

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
    @ViewInject(R.id.view_pager)
    private ViewPager myViewPager;
    private List<Fragment> fragments;

    public HomeFragment() {
    }

    @Override
    public void onInitView() {

        imageUrls = new ArrayList<>();
        homeDatas = new ArrayList<>();
        homeDatas.add(new HomeRes(R.mipmap.home_banner_icon, "广告预约"));
        homeDatas.add(new HomeRes(R.mipmap.home_data_analysis, "数据分析"));
        homeDatas.add(new HomeRes(R.mipmap.home_financial_management, "财务管理"));
        homeDatas.add(new HomeRes(R.mipmap.home_client_manager, "客户管理"));
        homeDatas.add(new HomeRes(R.mipmap.home_training_sales, "培训促销"));
        homeDatas.add(new HomeRes(R.mipmap.home_message_push, "消息推送"));
        homeDatas.add(new HomeRes(R.mipmap.home_select_center, "选机中心"));
        homeDatas.add(new HomeRes(R.mipmap.home_suppe_selectr, "供应查询"));
        homeDatas.add(new HomeRes(R.mipmap.home_order_select, "订单查询"));
        mGirdViewAdapter = new HomeGvAdapter(homeDatas, getActivity());
        mGridView.setAdapter(mGirdViewAdapter);


        fragments = new ArrayList<>();
        fragments.add(new NavbarFragment());
        fragments.add(new NavbarFragment());


        myViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Event(value = {R.id.grid_view}, type = AdapterView.OnItemClickListener.class)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.shows(getActivity(), homeDatas.get(position).getResName());
        Intent intent = new Intent();
        switch (position) {
            case 3:
                intent.setClass(getActivity(), ClientManagerActivity.class);
                startActivity(intent);
                break;
            case 5:
                intent.setClass(getActivity(), MessageCenterActivity.class);
                startActivity(intent);
                break;
            case 6:
                intent.setClass(getActivity(), ProduceListActivity.class);
                startActivity(intent);
                break;
            case 7:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case 8:
                intent.setClass(getActivity(), OrderManagerActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Event(value = {R.id.search_edittext})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_edittext:
                Intent intent = new Intent();
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onInitData() {
        HttpRequestUtils.getmInstance().post(getActivity(), Constant.BANNER_URL, null, new HttpRequestCallBack<String>() {
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
