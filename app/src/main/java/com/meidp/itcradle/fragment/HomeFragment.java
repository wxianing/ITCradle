package com.meidp.itcradle.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {

    public HomeFragment() {
    }

    @Override
    public void onInitData() {

    }

    @Event(value = R.id.search_btn)
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                break;
        }
    }


}
