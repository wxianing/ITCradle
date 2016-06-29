package com.meidp.itcradle.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.itcradle.R;
import com.meidp.itcradle.activity.PersonalInformationActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_my)
public class MyFragment extends BaseFragment {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    public MyFragment() {
    }


    @Override
    public void onInitView() {
        backImg.setVisibility(View.GONE);
        title.setText("我的");
    }

    @Event(value = {R.id.person_center})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_center:
                intent.setClass(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
