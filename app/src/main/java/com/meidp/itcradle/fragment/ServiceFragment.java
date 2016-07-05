package com.meidp.itcradle.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.itcradle.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.fragment_service)
public class ServiceFragment extends BaseFragment {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    public ServiceFragment() {

    }

    @Override
    public void onInitView() {
        super.onInitView();
        backImg.setVisibility(View.GONE);
        title.setText("客服与投诉");
    }

}
