package com.meidp.itcradle.http;

import com.android.volley.VolleyError;

import org.xutils.common.Callback;

/**
 * Package：com.meist.pinfan.utils
 * 作  用：
 * Author：wxianing
 * 时  间：2016/6/18
 */

public abstract class HttpRequestCallBack<ResultType> {

    public abstract void onSuccess(ResultType result);

    public void onFail(VolleyError volleyError) {
    }

    public void onError(Throwable ex, boolean isOnCallback) {
    }

    public void onCancelled(Callback.CancelledException cex) {

    }

    public void onFinished() {

    }

    public void onWaiting() {

    }

    public void onStarted() {

    }

    public void onLoading(long total, long current, boolean isDownloading) {

    }
}