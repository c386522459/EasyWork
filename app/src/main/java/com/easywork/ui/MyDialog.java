package com.easywork.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.easywork.R;

/**
 * Created by yangxike on 15/10/27.
 */
public class MyDialog extends Dialog {

    public MyDialog(Context context) {
        super(context, R.style.DialogExit);
        init();
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_exit);

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_dialog_title)).setText(title);
    }

    public void setOnSubmitClickListener(View.OnClickListener onClickListener) {
        findViewById(R.id.btn_submit).setOnClickListener(onClickListener);
    }

}
