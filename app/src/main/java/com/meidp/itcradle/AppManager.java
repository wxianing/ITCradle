package com.meidp.itcradle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.meidp.itcradle.model.User;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.LogUtils;
import com.meidp.itcradle.utils.SharedPreferencesUtils;
import com.meidp.itcradle.utils.ToastUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by John on 2016/7/7.
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;
    public static Application application;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack != null && activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取当前上一个Activity（堆栈中倒数第二个压入的）
     */
    public Activity prevActivity() {
        Activity activity = activityStack.get(activityStack.size() - 2);
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                Activity activity = activityStack.get(i);
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            /*
             * Intent intent = new Intent(context, MainActivity.class);
			 * PendingIntent restartIntent = PendingIntent.getActivity( context,
			 * 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK); //退出程序 AlarmManager
			 * mgr =
			 * (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			 * mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
			 * restartIntent); // 1秒钟后重启应用
			 */
            // 杀死该应用进程
            // android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    private static long firstTime;

    public void twoClickBack(Context context) {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
            ToastUtils.shows(context, "再按一次退出程序");
            firstTime = secondTime;// 更新firstTime
            return;
        } else { // 两次按键小于2秒时，退出应用
            AppExit(context);
        }
    }

    /**
     * 跳转系统拨号界面
     */
    public static void launchPhoneActivity(String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        AppManager.getAppManager().currentActivity().startActivity(intent);
    }

    /**
     * 跳转QQ对应好友聊天界面
     *
     * @param qq ＱＱ号
     */
    public static void launchQQAPP(String qq) {
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;
            AppManager.getAppManager().currentActivity()
                    .startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.shows(AppManager.getAppManager().currentActivity(), "无法启动QQ,请检查是否安装QQ");
        }

    }

    /**
     * 启动微信应用
     */
    public static void launchWechatApp() {
        launchThirdApp("com.tencent.mm");
    }



    public static void launchThirdApp(String packageName) {
        try {
            PackageManager packageManager = AppManager.getAppManager()
                    .currentActivity().getPackageManager();
            Intent intent = new Intent();
            intent = packageManager.getLaunchIntentForPackage(packageName);
            AppManager.getAppManager().currentActivity().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.shows(AppManager.getAppManager().currentActivity(), "无法启动该应用，请检查是否安装");
        }
    }

    /**
     * 调用文件选择软件来选择文件 *
     */
    public static void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            AppManager
                    .getAppManager()
                    .currentActivity()
                    .startActivityForResult(
                            Intent.createChooser(intent, null),
                            Constant.FILE_SELECT_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取当期应用版本号
     *
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 防止魅族手机底部菜单键挡住弹出的popupwindow
     *
     * @param v View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 隐藏底部菜单键
     *          View.SYSTEM_UI_FLAG_VISIBLE 默认状态
     */
    public static void setSystemUiVisibility(int v) {
        Activity ac = AppManager.getAppManager().currentActivity();
        ac.getWindow().getDecorView().setSystemUiVisibility(v);
    }
}
