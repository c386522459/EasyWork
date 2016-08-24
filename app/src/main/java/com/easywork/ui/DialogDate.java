package com.easywork.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.easywork.R;
import com.easywork.ui.wheelview.NumericWheelAdapter;
import com.easywork.ui.wheelview.OnWheelScrollListener;
import com.easywork.ui.wheelview.WheelView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27 0027.
 */
public class DialogDate extends Dialog {
    private WheelView wheelYear;
    private WheelView wheelMonth;
    private WheelView wheelDay;
    private TextView btnCancel;
    private TextView btnSubmit;
    private TextView tv_dialog_title;
    private static int START_YEAR = 2016;
    private int countYear;
    String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
    String[] months_little = { "4", "6", "9", "11" };
    private String year = "2016", month = "12";

    final List<String> list_big = Arrays.asList(months_big);
    final List<String> list_little = Arrays.asList(months_little);
    Calendar calendar = Calendar.getInstance();
    public DialogDate(Context context) {
        super(context);
        initWidget();

    }

    public DialogDate(Context context, int theme) {
        super(context, theme);
        initWidget();
    }
    public DialogDate(Context context,int yearCount,int START_YEAR,int theme){
        super(context,theme);
        countYear = yearCount;
        this.START_YEAR = START_YEAR;
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
        wheelYear.setAdapter(new NumericWheelAdapter(START_YEAR, START_YEAR + countYear));
        wheelYear.addScrollingListener(new wheelScrollListener());

        wheelMonth.setCyclic(true);
        wheelMonth.setVisibleItems(5);
        wheelMonth.setItemHeight(itemHeight);
        wheelMonth.setLabel("");
        wheelMonth.setOffSet((itemHeight) / 2);
        wheelMonth.setTextSize(textSize);
        wheelMonth.setAdapter(new NumericWheelAdapter(1, 12));
        wheelMonth.addScrollingListener(new wheelScrollListener());

        wheelDay.setCyclic(true);
        wheelDay.setVisibleItems(5);
        wheelDay.setItemHeight(itemHeight);
        wheelDay.setLabel("");
        wheelDay.setOffSet((itemHeight) / 2);
        wheelDay.setTextSize(textSize);
        wheelDay.addScrollingListener(new wheelScrollListener());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDate.this.dismiss();
            }
        });
    }

    /***
     * 初始化弹出框显示的日期和时间（获取系统当前的时间）
     */
    public void initCurDate() {
        wheelYear.setCurrentItem(calendar.get(Calendar.YEAR) - START_YEAR);
        wheelMonth.setCurrentItem(calendar.get(Calendar.MONTH));

        month = (wheelMonth.getCurrentItem() + 1) + "";
        setDayValue(Integer.valueOf(year), Integer.valueOf(month));
        wheelDay.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
    }

    private class wheelScrollListener implements OnWheelScrollListener {

        @Override
        public void onScrollingFinished(WheelView wheel) {
            // TODO Auto-generated method stub
            year = (wheelYear.getCurrentItem() + START_YEAR) + "";
            if (wheel == wheelYear || wheel == wheelMonth) {
                month = (wheelMonth.getCurrentItem() + 1) + "";
                setDayValue(Integer.valueOf(year), Integer.valueOf(month));
            }
        }

        @Override
        public void onScrollingStarted(WheelView wheel) {
            // TODO Auto-generated method stub
        }

    }

    private void setDayValue(int year, int month) {
        if (list_big.contains(String.valueOf(month))) {
            wheelDay.setAdapter(new NumericWheelAdapter(1, 31));
        } else if (list_little.contains(String.valueOf(month))) {
            wheelDay.setAdapter(new NumericWheelAdapter(1, 30));
        } else {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                wheelDay.setAdapter(new NumericWheelAdapter(1, 29));
            } else {
                wheelDay.setAdapter(new NumericWheelAdapter(1, 28));
            }
        }
    }

    /***
     * 设置弹出框弹出时显示的日期和时间
     *
     * @param curYear
     *            设置的年
     * @param curMonth
     *            设置的月
     * @param curDay
     *            设置的日
     */
    public void setCurDate(int curYear, int curMonth, int curDay) {
        wheelYear.setCurrentItem(curYear - START_YEAR);
        wheelMonth.setCurrentItem(curMonth - 1);
        year = (wheelYear.getCurrentItem() + START_YEAR) + "";
        month = (wheelMonth.getCurrentItem() + 1) + "";
        setDayValue(Integer.valueOf(year), Integer.valueOf(month));
        wheelDay.setCurrentItem(curDay - 1);
    }

    public void setBtnSubmit(View.OnClickListener onClickListener) {
        if (onClickListener != null)
            btnSubmit.setOnClickListener(onClickListener);
    }

    public String getDate() {
        String year = (wheelYear.getCurrentItem() + START_YEAR)
                + "-" + add0(wheelMonth.getCurrentItem() + 1)
                + "-" + add0(wheelDay.getCurrentItem() + 1);
        return year;
    }

    public int getYear() {
        return wheelYear.getCurrentItem() + START_YEAR;
    }

    public int getMonth() {
        return wheelMonth.getCurrentItem() + 1;
    }

    public int getDay() {
        return wheelDay.getCurrentItem() + 1;
    }

    private String add0(int size) {
        if (size < 10) {
            return "0" + size;
        }
        return size + "";
    }

    @Override
    public void setTitle(CharSequence title) {
        tv_dialog_title.setText(title);
    }
}
