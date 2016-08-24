package com.easywork.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.easywork.R;
import com.easywork.util.BaseTools;
import com.easywork.util.BaseUtil;

import static com.easywork.R.id.tv_dialog_title;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SimpleDialog extends Dialog{
    TextView dialog_content,btn_cancel,btn_submit,title;
    public SimpleDialog(Context context) {
        super(context);
        initWiget();
    }

    public SimpleDialog(Context context, int themeResId) {
        super(context, themeResId);
        initWiget();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = BaseTools.getScreenWidth(context)-100;
       getWindow().setAttributes(params);
    }

    protected SimpleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initWiget();
    }
    private void initWiget(){
        setContentView(R.layout.dialog_simple);
        btn_submit = (TextView) findViewById(R.id.btn_submit);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        dialog_content = (TextView) findViewById(R.id.dialog_content);
        title = (TextView) findViewById(R.id.tv_dialog_title);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    public void setLeftClick(String cancleString, View.OnClickListener cancleListener){
        btn_cancel.setText(cancleString);
        btn_cancel.setOnClickListener(cancleListener);
    }
    public void setRightClick(String submitString, View.OnClickListener submitListener){
        btn_submit.setText(submitString);
        btn_submit.setOnClickListener(submitListener);
    }
    public void setMsg(String titles,String content){
        title.setText(titles);
        dialog_content.setText(content);
    }
}
