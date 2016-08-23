package com.easywork.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by Administrator on 2016/1/14.
 */
public class BaseUtil {
    /**
     * 删除登录信息
     * @param mContext
     */
    public static void clearLogin(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.getAll().clear();
    }
    public static boolean isLogin(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        String token = mPreferences.getString("token","");
        return !BaseTools.isValueEmpty(token);
    }
    public static void setToken(Context context,String token){
        SharedPreferences mPreferences = context.getSharedPreferences(
                "easyData", context.MODE_PRIVATE);
       mPreferences.edit().putString("token",token).commit();

    }
    public static String getToken(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
      return mPreferences.getString("token","");
    }
    public static void setUserId(Context context,String userId){
        SharedPreferences mPreferences = context.getSharedPreferences(
                "easyData", context.MODE_PRIVATE);
        mPreferences.edit().putString("userId",userId).commit();

    }
    public static String getUserId(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("userId","");
    }
    public static String getPhone(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        String token = mPreferences.getString("phone","");
        return token;
    }
    public static  void setPhone(Context mContext,String phone){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
       mPreferences.edit().putString("phone",phone).commit();
    }


    public static void setAuth(Context mContext,String auth){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("auth",auth).commit();
    }

    public static String getAuth(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("auth","0");
    }
    public static void setCityName(Context mContext,String city){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("cityName",city).commit();
    }
    public static String getCityName(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("cityName","杭州");
    }
    public static void setCityCode(Context mContext,int cityCode){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putInt("cityCode",cityCode).commit();
    }
    public static int getCityCode(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getInt("cityCode",0);
    }

    public static String getNickName(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("nickName","");
    }
    public static void setNickName(Context mContext,String cityCode){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("nickName",cityCode).commit();
    }
    public static String getHeaderImg(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("header","");
    }
    public static void setHeaderImg(Context mContext,String headerImg){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("header",headerImg).commit();
    }
    public static void setQniu(Context mContext,String headerImg){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("qiniu",headerImg).commit();
    }
    public static String getQniu(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("qiniu","");
    }
    public static void setQniuUrl(Context mContext,String headerImg){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        if(headerImg.endsWith("/")){
            headerImg = headerImg.substring(0,headerImg.length()-1);
        }
        mPreferences.edit().putString("qiniuUrl",headerImg).commit();
    }
    public static String getQniuUrl(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("qiniuUrl","http://7xt788.com1.z0.glb.clouddn.com");
    }

    public static String getLat(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("lat","");
    }
    public static void setLat(Context mContext,String lat){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("lat",lat).commit();
    }
    public static String getLng(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getString("lng","");
    }
    public static void setLng(Context mContext,String lat){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putString("lng",lat).commit();
    }
    public static void setStatus(Context context,String status){
        SharedPreferences mPreferences = context.getSharedPreferences(
                "easyData", context.MODE_PRIVATE);
        mPreferences.edit().putString("status",status).commit();
    }
    public static String getStatus(Context context){
        SharedPreferences mPreferences = context.getSharedPreferences(
                "easyData", context.MODE_PRIVATE);
        return mPreferences.getString("status","1");
    }
    public static float getBalance(Context mContext){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        return mPreferences.getFloat("balance",0);
    }
    public static void setBalance(Context mContext,double headerImg){
        SharedPreferences mPreferences = mContext.getSharedPreferences(
                "easyData", mContext.MODE_PRIVATE);
        mPreferences.edit().putFloat("balance", (float) headerImg).commit();
    }
}
