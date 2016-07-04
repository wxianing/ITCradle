package com.meidp.itcradle.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.itcradle.MyApplication;
import com.meidp.itcradle.R;
import com.meidp.itcradle.model.Information;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John on 2016/7/4.
 */
public class InformationAdapter extends BasicAdapter<Information.DataListBean> {
    public InformationAdapter(List<Information.DataListBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Information.DataListBean data = mDatas.get(position);
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_message_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.title.setText(data.getTitle());
        ImageLoader.getInstance().displayImage(data.getImgPath(), vh.img, MyApplication.options);
        return convertView;
    }

    private static class ViewHolder {
        @ViewInject(R.id.img)
        private ImageView img;
        @ViewInject(R.id.title_text)
        private TextView title;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
