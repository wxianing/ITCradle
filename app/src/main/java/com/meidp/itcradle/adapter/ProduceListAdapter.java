package com.meidp.itcradle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.itcradle.MyApplication;
import com.meidp.itcradle.R;
import com.meidp.itcradle.model.Produce;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by John on 2016/7/6.
 */
public class ProduceListAdapter extends BasicAdapter<Produce.DataListBean> {
    public ProduceListAdapter(List<Produce.DataListBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        Produce.DataListBean data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_produce_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(data.getThumbImg(), vh.img, MyApplication.options);
        vh.produceName.setText(data.getGoodName());
        vh.clickVolume.setText("点击量：" + Integer.toString(data.getHits()));
        vh.mediaPrice.setText("￥" + data.getMarketPrice());
        vh.salesVolume.setText("销售量：" + Integer.toString(data.getBuyCount()));
        vh.priceIndices.setText("￥" + data.getSalePrice());
        return convertView;
    }

    private static class ViewHolder {

        @ViewInject(R.id.img)
        private ImageView img;
        @ViewInject(R.id.produce_text)
        private TextView produceName;
        @ViewInject(R.id.sales_volume)
        private TextView salesVolume;
        @ViewInject(R.id.click_volume)
        private TextView clickVolume;
        @ViewInject(R.id.media_price)
        private TextView mediaPrice;
        @ViewInject(R.id.price_indices)
        private TextView priceIndices;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
