package com.easywork.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/1/9.
 */
public class BaseTools {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    /***
     * 用来判断文件是否存在
     *
     * @param filepath
     *            文件路径
     * @return
     */
    public static boolean isFileExist(String filepath) {
        File f = new File(filepath);
        if (!f.exists())
            return false;
        return true;
    }

    /***
     * 用来判断是否联网
     *
     * @author Pro.Linyl
     * @CreateTime 2015年6月15日
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @param context
     * @return
     */
    public static boolean isConnection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }
    /**
     * 获得圆角图片的方法
     *
     * @param bitmap
     * @param roundPx
     *            一般设成14
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    /***
     * 获取视图高度
     *
     * @param v
     * @return
     */
    public static int getViewHeight(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredHeight();
    }
    /***
     *
     * @author Pro.Linyl
     * @CreateTime 2015年6月5日
     * @UpdateAuthor Pro.Linyl
     * @UpdateTime 2015年6月5日
     * @description 获取当前应用程序的版本号
     *
     * @param context application
     * @return 版本名,版本号（versionName+ "," + versionCode）
     */
    public static String getVersion(Application context) {
        String versionInfo = "";
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(), 0);
            versionInfo += packinfo.versionName;
            versionInfo += ("," + packinfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionInfo;
    }
    /***
     * 判断字符串非空，非null
     * @author Pro.Linyl
     * @CreateTime 2015年6月18日
     * @UpdateAuthor
     * @UpdateTime
     * @description
     *
     * @param text 需要判断的字符串
     * @return
     */
    public static boolean isValueEmpty(String text) {
        if (text != null && !text.trim().equals("") && !text.trim().equals("null")) {
            return false;
        }
        return true;
    }
    public static boolean isValueEmpty(Collection<?> mList){
        if (mList == null || mList.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 手机号验证
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if(TextUtils.isEmpty(mobiles)){
            return false;
        }
        Pattern p = Pattern.compile("^[1][3-8]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 隐藏键盘
     *
     * @param mcontext
     * @param v
     */
    public static void hideSystemKeyBoard(Context mcontext, View v) {
        InputMethodManager imm = (InputMethodManager) mcontext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    public static int getScreenWidth(Context mContext){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
       return dm.widthPixels;
    }

    public static int dp2px(Context mContext, int dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }
    public static int px2dp(Context mContext,int pxValue){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }
    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context mContext, float spValue) {
        float scaleDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaleDensity + 0.5f);
    }
    /**
     * @Description 解析父对象为Object对象
     * @param cla
     *            要解析的对象
     * @return T
     */
    public static <T> T toObject(String data ,Class<T> cla) {
        if(TextUtils.isEmpty(data)){
            try {
                return cla.newInstance();
            } catch (InstantiationException e) {
                Log.d("fastjson解析错误", e.getMessage());
            } catch (IllegalAccessException e) {
                Log.d("fastjson解析错误", e.getMessage());
            }
        }
        try {
            return JSON.parseObject(data, cla);
        } catch (Exception e) {
            Log.d("fastjson解析错误", e.getMessage());
            return null;
        }
    }

    /**
     * 根据生日获取年龄
     * @param birthday
     * @return
     */
    public static int getOld(String birthday){
        try {
            int birYear = dateFormat.parse(birthday).getYear();
            int nowYear = new Date().getYear();
            return nowYear-birYear+1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static String date2String(Date date){
        try {
           return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static Date string2date(String data){
        try {
            return timeFormat.parse(data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
