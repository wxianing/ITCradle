package com.meidp.itcradle.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.itcradle.R;
import com.meidp.itcradle.http.HttpRequestCallBack;
import com.meidp.itcradle.http.HttpRequestUtils;
import com.meidp.itcradle.model.AppBean;
import com.meidp.itcradle.model.User;
import com.meidp.itcradle.utils.Constant;
import com.meidp.itcradle.utils.LogUtils;
import com.meidp.itcradle.utils.NullUtils;
import com.meidp.itcradle.utils.SharedPreferencesUtils;
import com.meidp.itcradle.utils.ToastUtils;

import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SocketHandler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * A login screen that offers login via email/password.
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements PlatformActionListener {
    @ViewInject(R.id.username_et)
    private EditText usernameEt;
    @ViewInject(R.id.password_et)
    private EditText passwordEt;
    private String userName;
    private String passWord;
    @ViewInject(R.id.check_box)
    private CheckBox checkBox;

    private boolean isRmb = false;
    private int thirdType;
    private Platform pla;
    private ProgressDialog loadingDialog;

    @Override
    public void onInitView() {

        userName = SharedPreferencesUtils.getStringData(this, "USERNAME", null);
        passWord = SharedPreferencesUtils.getStringData(this, "PASSWORD", null);
        isRmb = SharedPreferencesUtils.getbooleanData(this, "ISRMB", false);
        if (NullUtils.isNull(userName)) {
            usernameEt.setText(userName);
        }

        if (isRmb && NullUtils.isNull(passWord)) {
            passwordEt.setText(passWord);
            checkBox.setChecked(true);
        }
    }

    @Event(value = {R.id.perfect_information, R.id.login_btn, R.id.weixin_img, R.id.qq_img})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.perfect_information:
                intent.setClass(this, PerfectInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                userName = usernameEt.getText().toString();
                passWord = passwordEt.getText().toString();
                if (NullUtils.isNull(userName) && NullUtils.isNull(passWord)) {
                    HashMap map = new HashMap<>();
                    map.put("UserName", userName);
                    map.put("Password", passWord);
                    HttpRequestUtils.getmInstance().post(LoginActivity.this, Constant.LOGIN_URL, map, new HttpRequestCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                            });
                            if (null != appBean && appBean.getEnumcode() == 0) {
                                ToastUtils.shows(LoginActivity.this, "登录成功");
                                SharedPreferencesUtils.saveStringData(LoginActivity.this, "CODE", appBean.getData().getCode());
                                SharedPreferencesUtils.saveStringData(LoginActivity.this, "USERNAME", userName);
                                SharedPreferencesUtils.saveStringData(LoginActivity.this, "PASSWORD", passWord);
                                SharedPreferencesUtils.saveUser(LoginActivity.this, appBean.getData());//保存User对象
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                ToastUtils.shows(LoginActivity.this, appBean.getMsg());
                            }
                        }
                    });

                } else {
                    ToastUtils.shows(this, "用户名或密码不能为空！");
                }

                break;
            case R.id.weixin_img:
                Platform wechart = ShareSDK.getPlatform(Wechat.NAME);
                thirdType = Constant.WECHAT;
                authorize(wechart);
                loadingDialog = new ProgressDialog(this);
                loadingDialog.show();
                break;
            case R.id.qq_img:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                thirdType = Constant.QQ;
                authorize(qq);
                loadingDialog = new ProgressDialog(this);
                loadingDialog.show();
                break;
        }
    }

    /**
     * CheckBox事件监听处理
     *
     * @param buttonView
     * @param isChecked
     */
    @Event(value = R.id.check_box, type = CompoundButton.OnCheckedChangeListener.class)
    private void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.check_box) {
            SharedPreferencesUtils.savebooleanData(this, "ISRMB", isChecked);
        }
    }

    private void authorize(Platform plat) {
        if (plat.isValid()) {
            plat.removeAccount();
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.pla = platform;
        LogUtils.e(hashMap.toString());
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                LogUtils.e("授权成功");
                LogUtils.e("userId:" + pla.getDb().getUserId());
                String accountId = pla.getDb().getUserId();
                String userName = pla.getDb().getUserName();
                String headerIcon = pla.getDb().getUserIcon();

                HashMap params = new HashMap();
                params.put("AccountId", accountId);
                params.put("AccountType", thirdType);
                params.put("NickName", userName);
                params.put("HeadPhoto", headerIcon);

                HttpRequestUtils.getmInstance().post(LoginActivity.this, Constant.LOGIN_BY_THIRD, params, new HttpRequestCallBack() {

                    @Override
                    public void onSuccess(String result) {

                    }
                });

                dismissLoadingDialog();
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.shows(LoginActivity.this, "取消授权");
                dismissLoadingDialog();
            }
        });
    }


    private void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}

