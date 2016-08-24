package com.easywork.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.easywork.R;
import com.easywork.adapter.SimpleStringAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/18.
 */
public class PopWindow extends Dialog{
    private Context mContext;
    private TextView pop_title;
    private ListView pop_list;
    public PopWindow(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        initView();
    }

    public PopWindow(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        initView();
    }

    void  initView(){
       setContentView(R.layout.pop_center_text);
        getWindow().setGravity(Gravity.CENTER);
       pop_title = (TextView) findViewById(R.id.pop_title);
       pop_list = (ListView) findViewById(R.id.pop_list);
    }
    public void setData(List<String> mList){
        pop_list.setAdapter(new SimpleStringAdapter(mContext,mList));
    }
    public void setTitleGone(){
        pop_title.setVisibility(View.GONE);
        findViewById(R.id.pop_line).setVisibility(View.GONE);
    }
    @Override
    public void setTitle(CharSequence title) {
        pop_title.setText(title);
    }

    public PopWindow(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TextView getPop_title() {
        return pop_title;
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        pop_list.setOnItemClickListener(onItemClickListener);
    }
}
