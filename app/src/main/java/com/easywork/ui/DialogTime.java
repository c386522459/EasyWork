package com.easywork.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.easywork.R;
import com.easywork.ui.wheelview.ArrayWheelAdapter;
import com.easywork.ui.wheelview.NumericWheelAdapter;
import com.easywork.ui.wheelview.OnWheelScrollListener;
import com.easywork.ui.wheelview.WheelView;
import com.easywork.util.BaseTools;
import com.easywork.util.BaseUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/26.
 */
public class DialogTime extends Dialog{
    private WheelView wheelYear;
    private WheelView wheelMonth;
    private WheelView wheelDay;
    private TextView btnCancel;
    private TextView btnSubmit;
    private TextView tv_dialog_title;
    private List<String> dayString = new ArrayList<>();
    public DialogTime(Context context) {
        super(context);
        initWidget();
    }

    public DialogTime(Context context, int themeResId) {
        super(context, themeResId);
        initWidget();
    }

    protected DialogTime(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initWidget();
    }
    private void initWidget() {
        setContentView(R.layout.dialog_choose_date);
        Window window = getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        onWindowAttributesChanged(wl);
        // 设置显示位置
        window.setGravity(Gravity.BOTTOM);
        getDay();
        wheelYear = (WheelView) findViewById(R.id.wheel_year);
        wheelMonth = (WheelView) findViewById(R.id.wheel_month);
        wheelDay = (WheelView) findViewById(R.id.wheel_day);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnSubmit = (TextView) findViewById(R.id.btn_submit);
        tv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        int itemHeight = (int)getContext().getResources().getDimension(R.dimen.wheel_item_height);
        int textSize = (int)getContext().getResources().getDimension(R.dimen.wheel_item_text);

        wheelYear.setCyclic(true);
        wheelYear.setVisibleItems(5);
        wheelYear.setItemHeight(itemHeight);
        wheelYear.setLabel("");
        wheelYear.setOffSet((itemHeight) / 2);
        wheelYear.setTextSize(textSize);
        wheelYear.setAdapter(new ArrayWheelAdapter<String>(dayString));

        wheelMonth.setCyclic(true);
        wheelMonth.setVisibleItems(5);
        wheelMonth.setItemHeight(itemHeight);
        wheelMonth.setLabel("");
        wheelMonth.setOffSet((itemHeight) / 2);
        wheelMonth.setTextSize(textSize);
        wheelMonth.setAdapter(new NumericWheelAdapter(00, 23));

        wheelDay.setCyclic(true);
        wheelDay.setVisibleItems(5);
        wheelDay.setItemHeight(itemHeight);
        wheelDay.setLabel("");
        wheelDay.setOffSet((itemHeight) / 2);
        wheelDay.setTextSize(textSize);
        wheelDay.setAdapter(new NumericWheelAdapter(00, 59));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTime.this.dismiss();
            }
        });
    }
    public void setBtnSubmit(View.OnClickListener onClickListener) {
        if (onClickListener != null)
            btnSubmit.setOnClickListener(onClickListener);
    }

    private void getDay(){
        Calendar now = Calendar.getInstance();
        dayString.add(BaseTools.date2String(now.getTime()));
        for(int i=1;i<90;i++){
            now.add(Calendar.DATE,1);
            dayString.add(BaseTools.date2String(now.getTime()));
        }
    }
    public String getTime(){
        String time = dayString.get(wheelYear.getCurrentItem())
                + " " + add0(wheelMonth.getCurrentItem())
                + ":" + add0(wheelDay.getCurrentItem());
        return time;
    }
    private String add0(int size) {
        if (size < 10) {
            return "0" + size;
        }
        return size + "";
    }
    public void setCurDate(String time){
        Date currentDate = BaseTools.string2date(time);
        String currentDay = BaseTools.date2String(currentDate);
        wheelYear.setCurrentItem(dayString.indexOf(currentDay));
        wheelDay.setCurrentItem(currentDate.getHours());
        wheelDay.setCurrentItem(currentDate.getMinutes());
    }
    public void setDialogTitle(String title){
        tv_dialog_title.setText(title);
    }
}
