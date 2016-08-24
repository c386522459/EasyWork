package com.easywork.ui.wheelview;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easywork.R;

/**
 * Created by Administrator on 2016/6/11 0011.
 */
public class LoadingBar extends Dialog{
    private Context context = null;
    AnimationDrawable animationDrawable;
    public LoadingBar(Context context){
        super(context,R.style.CustomProgressDialog);
        this.context = context;
        setContentView(R.layout.progressbar_loading);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        ImageView imageView = (ImageView) findViewById(R.id.loadingImageView);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        animationDrawable.stop();
        super.dismiss();
    }

    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public void  setMessage(String strMessage){
        TextView tvMsg = (TextView)findViewById(R.id.id_tv_loadingmsg);
        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }
    }
}
