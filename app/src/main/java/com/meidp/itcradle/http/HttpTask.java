package com.meidp.itcradle.http;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.AppManager;
import com.meidp.itcradle.R;
import com.meidp.itcradle.model.AppBean;
import com.meidp.itcradle.model.Versions;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.ToastUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/2/27.
 */
public class HttpTask {
    public static void detectionNewAppVersion(final Context context, final boolean isUpdate, final boolean showLoading) {

        HashMap params = new HashMap();
        params.put("_appId", Constant.APPID);
        params.put("_code", Constant.CODE);
        params.put("AppId", Constant.APPID);

        HttpRequestUtils.getmInstance().post(context, Constant.GET_NEW_VERSION, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                final AppBean<Versions> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Versions>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    int versionCode = Integer.parseInt(appBean.getData().getVersionCode());
                    if (versionCode > AppManager.getAppVersionCode(context)) {
                        if (isUpdate) {
                            {
                                if (isUpdate) {
                                    new AlertDialog.Builder(context).setTitle("版本升级")
                                            .setMessage("检测到有新版本，是否升级？")
                                            .setPositiveButton("是", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    updateApp(context, appBean.getData());
                                                }
                                            }).setNegativeButton("否", null).create().show();
                                } else {
                                    //发送广播刷新设置界面的新版本显示
                                    Intent intent = new Intent(Constant.ACTION_NEW_VERSION);
                                    intent.putExtra(Constant.VERSION_NAME, appBean.getData().getVersionName());
                                    context.sendBroadcast(intent);
                                }
                                if (showLoading) {
                                    ToastUtils.shows(context, "已是最新版本");
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public static void updateApp(final Context context, Versions app) {
        final String appName = context.getString(R.string.app_name);
        final String savePath = Environment.getExternalStorageDirectory()
                .toString()
                + File.separator
                + appName
                + File.separator
                + appName
                + app.getVersionName() + ".apk";

        HttpRequestUtils.getmInstance().downLoadFile(app.getFilePath(), savePath, new MyProgressCallBack<File>() {
            private NotificationManager updateNotificationManager;
            private Notification updateNotification;
            private NotificationCompat.Builder mBuilder;

            @Override
            public void onSuccess(File result) {
                super.onSuccess(result);
            }

            @Override
            public void onStarted() {
                super.onStarted();
                // 创建文件
                updateNotificationManager = (NotificationManager) context
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setContentTitle("正在更新" + appName);
                mBuilder.setContentText("0%");
                mBuilder.setProgress(100, 0, false);
                mBuilder.setSmallIcon(android.R.drawable.stat_sys_download);
                updateNotification = mBuilder.build();
                updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
                updateNotificationManager.notify(101, updateNotification);
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                super.onLoading(total, current, isDownloading);
                mBuilder.setContentText((int) current * 100 / total + "%");
                mBuilder.setProgress(100, (int) (current * 100 / total), false);
                updateNotification = mBuilder.build();
                updateNotificationManager.notify(101, updateNotification);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mBuilder.setContentText("下载失败.请重新下载！");
                updateNotification = mBuilder.build();
                updateNotificationManager.notify(101, updateNotification);
            }
        });
    }
}
