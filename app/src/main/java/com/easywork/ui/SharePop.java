package com.easywork.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.easywork.R;
import com.easywork.util.BaseActivity;
import com.easywork.util.Constrants;
import com.easywork.util.FileUtil;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SharePop extends Dialog {
    private IWXAPI api;
    private Activity mActivity;
    private String content="快臣生活APP，是浙江快工网络科技有限公司旗下第一品牌，是以线下实体店为核心，以互联网+思维为驱动的020服务平台。平台以“快工”为主。“快商”、“快婚”、“快租售”、“快车交易”、“快店”等为辅打造便民生活的服务平台。";
    public SharePop(Context context) {
        super(context);
        mActivity = (Activity) context;
        initWegit();
    }

    public SharePop(Context context, int themeResId) {
        super(context, themeResId);
        mActivity = (Activity) context;
        initWegit();
    }

    protected SharePop(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mActivity = (Activity) context;
        initWegit();
    }


    protected void initWegit() {
        setContentView(R.layout.pop_share_board);
        WindowManager m = mActivity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参值
        p.width = (int) (d.getWidth() * 1.0); // 宽度设置为屏幕的1.0
        p.alpha = 1.0f; // 设置本身透明度
        p.dimAmount = 0.5f; // 设置黑暗度
        getWindow().setAttributes(p); // 设置生效
        getWindow().setGravity(Gravity.BOTTOM); // 设置靠右对齐
        api = WXAPIFactory.createWXAPI(mActivity, Constrants.APP_ID, true);
       // mTencent = Tencent.createInstance("tencent1104505320", this.getApplicationContext());*/
        findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qqShare();
            }
        });
        findViewById(R.id.qzone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qzoneShare();
            }
        });
        findViewById(R.id.wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wxShare();
            }
        });
        findViewById(R.id.pyq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wxcirShare();
            }
        });
    }

    public void qqShare(){
        Bundle bundle = new Bundle();
      /*  bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE,"测试");
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "来自来停车");
        bundle.putString(QQShare.SHARE_TO_QQ_APP_NAME,"来停车");
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"https://mmbiz.qlogo.cn/mmbiz/ic9ThkjYFwRrnGic6z6Y4ZXp320UDthv83Jh7qzLdMZKK038zpWFESXq0LovO0m6hib7G2xcV7DONmrb8Z7GmDOBw/0?wx_fmt=png");
        mTencent.shareToQQ(this, bundle , qqShareListener);*/
    }
    public void qzoneShare(){
       Bundle bundle = new Bundle();
     /*   bundle.putString(QzoneShare.SHARE_TO_QQ_TITLE,"测试");
        bundle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        bundle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "来自来停车");
        bundle.putString(QzoneShare.SHARE_TO_QQ_APP_NAME,"来停车");
        bundle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bundle.putString(QzoneShare.SHARE_TO_QQ_IMAGE_URL,"https://mmbiz.qlogo.cn/mmbiz/ic9ThkjYFwRrnGic6z6Y4ZXp320UDthv83Jh7qzLdMZKK038zpWFESXq0LovO0m6hib7G2xcV7DONmrb8Z7GmDOBw/0?wx_fmt=png");
        mTencent.shareToQzone(this, bundle , qqShareListener);*/
    }
    public void wxShare(){
      Bitmap bmp = BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.ic_launcher);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        msg.description=content;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        bmp.recycle();
         msg.thumbData = FileUtil.bmpToByteArray(thumbBmp, true);
        msg.title="测试";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img"+String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);

    }
    public void wxcirShare(){
       Bitmap bmp = BitmapFactory.decodeResource(mActivity.getResources(),R.drawable.ic_launcher);
        WXImageObject imgObj = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        msg.description=content;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
        bmp.recycle();
        msg.thumbData = FileUtil.bmpToByteArray(thumbBmp, true);
        msg.title="测试";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img"+String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }


/*
    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {
                toast("授权失败");
        }
        @Override
        public void onComplete(Object response) {
            // TODO Auto-generated method stub
          toast("分享成功");
        }
        @Override
        public void onError(UiError e) {
            // TODO Auto-generated method stub
            toast("onError:"+e.errorMessage);
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        }
    }*/
}
