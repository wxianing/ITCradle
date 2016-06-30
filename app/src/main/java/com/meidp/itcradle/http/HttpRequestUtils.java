package com.meidp.itcradle.http;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.meidp.itcradle.MyApplication;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.CustomDialogUtils;


import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class HttpRequestUtils {

    private static HttpRequestUtils mInstance;

    public HttpRequestUtils() {
    }

    public static synchronized HttpRequestUtils getmInstance() {
        if (mInstance == null) {
            synchronized (HttpRequestUtils.class) {
                if (mInstance == null) {
                    mInstance = new HttpRequestUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * Volley方式请求数据
     *
     * @param mContext
     * @param url
     * @param params
     * @param listener
     */
    public void send(Context mContext, String url, HashMap params, final HttpRequestCallBack listener) {
        CustomDialogUtils.showProgressDialog(mContext);
        Log.e("addParams:", JSON.toJSONString(params));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, JSON.toJSONString(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", response.toString());
                listener.onSuccess(response.toString());
                CustomDialogUtils.cannelProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onFail(volleyError);
                CustomDialogUtils.cannelProgressDialog();
            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("_appId", Constant.APPID);
                headers.put("_code", Constant.CODE);
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getmInstance().addToRequestQueue(request);
    }

    /**
     * 发送get请求
     */

    public void Get(Context mContext, String url, Map<String, String> map, final HttpRequestCallBack mCallBack) {
        CustomDialogUtils.showProgressDialog(mContext);
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mCallBack.onSuccess(result);
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onFinished() {
                CustomDialogUtils.cannelProgressDialog();
            }
        });
    }

    /**
     * 发送post请求
     */
    public void Post(Context mContext, String url, Map<String, Object> map, final HttpRequestCallBack mCallBack) {
        CustomDialogUtils.showProgressDialog(mContext);
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mCallBack.onSuccess(result);
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                CustomDialogUtils.cannelProgressDialog();
            }

            @Override
            public void onFinished() {
                CustomDialogUtils.cannelProgressDialog();
            }
        });
    }


    /**
     * 上传文件
     */
    public void UpLoadFile(String url, Map<String, Object> map) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 下载文件
     */
    public void DownLoadFile(String url, String filepath) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
