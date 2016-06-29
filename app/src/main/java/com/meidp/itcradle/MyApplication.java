package com.meidp.itcradle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.meidp.itcradle.http.OkHttpStack;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Package：com.meist.pinfan.utils
 * 作  用：
 * Author：wxianing
 * 时  间：2016/6/18
 */
public class MyApplication extends Application {

    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options;

    public static final String TAG = "VolleyPatterns";
    private RequestQueue mRequestQueue;
    private static MyApplication mInstance;
    private List<Activity> activitys = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        initImageLoader(getApplicationContext());
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.default_icon)//加载等待 时显示的图片
                .showImageForEmptyUri(R.mipmap.default_icon)//加载数据为空时显示的图片
                .showImageOnFail(R.mipmap.default_icon)//加载失败时显示的图片
                .cacheInMemory()
                .cacheOnDisc()
                .build();
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        // imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public static synchronized MyApplication getmInstance() {
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void addActivity(Activity activity) {
         activitys.add(activity);
    }

    public void removeActivity(Activity activity) {
           activitys.remove(activity);
    }

    public void closeActivitys() {
        ListIterator<Activity> iterator = activitys.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
