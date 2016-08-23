package com.easywork.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.easywork.Model.BaseResult;
import com.easywork.R;
import com.easywork.http.IHttpClinetListener;
import com.easywork.ui.wheelview.LoadingBar;
import com.umeng.analytics.MobclickAgent;


/**
 * Created by Administrator on 2016/1/9.
 */
public abstract class BaseActivity extends FragmentActivity implements IHttpClinetListener {
    public FrameLayout contentLayout;
    private RelativeLayout load_or_ero;
    private TextView title_name, right_text, left_tv;
    private ImageButton left_iv, right_iv;
    private RelativeLayout title_layout;
    private LoginBroadcast mLoginRecever;
    public String action;
    public BaseResult mBaseResult;
    public LoadingBar progressBar;
    private Toast to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setUpui(savedInstanceState);
        mLoginRecever = new LoginBroadcast();
        registerReceiver(mLoginRecever, new IntentFilter(Constrants.LOGIN_CAST));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mLoginRecever);
    }

    protected abstract void setUpui(Bundle arg0);

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        contentLayout = (FrameLayout) findViewById(R.id.base_content);
        load_or_ero = (RelativeLayout) findViewById(R.id.load_and_ero);
        title_layout = (RelativeLayout) findViewById(R.id.title_ll);
        initTitle(title_layout);
        View.inflate(this, layoutResID, contentLayout);
    }

    private void initTitle(View view) {
        title_name = (TextView) view.findViewById(R.id.title_name);
        right_text = (TextView) view.findViewById(R.id.right_text);
        left_iv = (ImageButton) view.findViewById(R.id.left_iv);
        right_iv = (ImageButton) view.findViewById(R.id.right_iv);
        left_tv = (TextView) view.findViewById(R.id.left_tv);

        left_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int top = getResources()
                .getDimensionPixelOffset(R.dimen.header_height);
        ((FrameLayout.LayoutParams) contentLayout.getLayoutParams()).topMargin = top;
    }

    public void setTitleGone(int visibility) {
        title_layout.setVisibility(visibility);
        if (visibility != View.GONE) {
            int top = getResources().getDimensionPixelOffset(
                    R.dimen.header_height);
            ((FrameLayout.LayoutParams) contentLayout.getLayoutParams()).topMargin = top;
        } else {
            ((FrameLayout.LayoutParams) contentLayout.getLayoutParams()).topMargin = 0;
        }
    }

    public void setTitlebg() {
        title_layout.setBackgroundResource(R.color.white);
    }

    public void settitleColor(int id) {
        title_name.setTextColor(getResources().getColor(id));
    }

    public void setRightTextVisible(int visibleAble) {
        right_text.setVisibility(visibleAble);
    }

    public void setRightTextClick(View.OnClickListener click) {
        right_text.setOnClickListener(click);
    }

    public void setLeftImage(int resouceId) {
        left_iv.setImageResource(resouceId);
    }

    public void setLeftGone() {
        left_iv.setVisibility(View.GONE);
    }

    public void setLeftClickListener(View.OnClickListener onClickListener) {
        left_tv.setOnClickListener(onClickListener);
    }

    public void setRightListener(View.OnClickListener rightListener) {
        right_text.setOnClickListener(rightListener);
        right_iv.setOnClickListener(rightListener);
    }

    public void setTitle(String title) {
        title_name.setText(title);
    }

    public void setRightText(String rightText) {
        right_text.setVisibility(View.VISIBLE);
        right_text.setText(rightText);
    }

    public void setTopName(
            String backTitle,
            String titleName) {
        if (!BaseTools.isValueEmpty(backTitle)) {
            left_tv.setVisibility(View.VISIBLE);
            left_tv.setText(backTitle);
        }
        title_name.setText(titleName);
    }

    public void toast(String text) {
        if (to == null) {
            to = new Toast(this);
        }
        if (!BaseTools.isValueEmpty(text)) {
            to.cancel();
            to = Toast.makeText(this, text, Toast.LENGTH_SHORT);
            to.show();
        }
    }

    @Override
    public void httpErrListener(String errMsg) {
        dissmissLoading();
        toast(errMsg);
    }

    @Override
    public void httpSuccessListener(Bundle successMsg) {
        dissmissLoading();
        action = successMsg.getString("action", "");
        String data = successMsg.getString("data", "");
        mBaseResult = BaseTools.toObject(data, BaseResult.class);
        if (mBaseResult == null) {
            mBaseResult = new BaseResult();
            mBaseResult.setResult("error");
            mBaseResult.setMsg("连接超时");
        }
        if (mBaseResult.getResult().equalsIgnoreCase("error") && mBaseResult.getMsg().contains("重新登陆")) {
            BaseUtil.setToken(this, "");
            Intent mIntent = new Intent();
            mIntent.setAction(Constrants.LOGIN_CAST);
            mIntent.putExtra("login", false);
            sendBroadcast(mIntent);
        }
    }

    public void showActivity(Class toClass, Bundle mBundle) {
        Intent mIntent = new Intent();
        mIntent.setClass(this, toClass);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivity(mIntent);
    }

    public void showActivityResult(Class toClass, Bundle mBundle, int resultCode) {
        Intent mIntent = new Intent();
        mIntent.setClass(this, toClass);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivityForResult(mIntent, resultCode);
    }

    public void KipActivity(Class toClass, Bundle mBundle) {
        showActivity(toClass, mBundle);
        finish();
    }

    class LoginBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean login = intent.getBooleanExtra("login", false);
            updateUI(login);
        }
    }

    public void setLeftMagin() {
        left_tv.setPadding(15, 0, 0, 15);
    }

    protected abstract void updateUI(boolean isLogin);

    public void base_back_bottom(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            finish();
        }
    }

    public String getTitleName() {
        return title_name.getText().toString();
    }

    public void showLoading(String loading) {
        if (progressBar == null) {
            progressBar = new LoadingBar(this);
        }
        progressBar.setMessage(loading);
        progressBar.show();
    }

    public void dissmissLoading() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
