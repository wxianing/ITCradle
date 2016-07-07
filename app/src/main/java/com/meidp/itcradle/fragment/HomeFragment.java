package com.meidp.itcradle.fragment;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
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

    @ViewInject(R.id.desktop_computer)
    private TextView desktopComputer;
    @ViewInject(R.id.notebook_computer)
    private TextView notebookComputer;
    @ViewInject(R.id.service_computer)
    private TextView seriverComputer;

    private Resources resources;
    private ColorStateList colorBlack;
    private ColorStateList colorBlue;

    private String type = "";
    @ViewInject(R.id.search_edittext)
    private EditText searchEdittext;

    private String keyWord;

    public HomeFragment() {
    }

    @Override
    public void onInitView() {
        resources = getActivity().getBaseContext().getResources();
        colorBlack = resources.getColorStateList(R.color.textcolor_black);
        colorBlue = resources.getColorStateList(R.color.textcolor_blue);
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

    @Event(value = {R.id.search_btn, R.id.desktop_computer, R.id.notebook_computer, R.id.service_computer})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.search_btn:
                keyWord = searchEdittext.getText().toString().trim();
                intent.setClass(getActivity(), SearchActivity.class);
                intent.putExtra("KEYWORD", keyWord);
                intent.putExtra("TYPE", type);
                startActivity(intent);
                type = "";
                break;
            case R.id.desktop_computer:
                resetTextColor();
                desktopComputer.setTextColor(colorBlue);
                type = "";
                type = "台式机";
                break;
            case R.id.notebook_computer:
                resetTextColor();
                notebookComputer.setTextColor(colorBlue);
                type = "";
                type = "笔记本";
                break;
            case R.id.service_computer:
                resetTextColor();
                seriverComputer.setTextColor(colorBlue);
                type = "";
                type = "服务器";
                break;
        }
    }

    /**
     * 重置字体颜色
     */
    private void resetTextColor() {
        desktopComputer.setTextColor(colorBlack);
        notebookComputer.setTextColor(colorBlack);
        seriverComputer.setTextColor(colorBlack);
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
