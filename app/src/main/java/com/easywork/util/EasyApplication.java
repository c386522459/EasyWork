package com.easywork.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/2/24.
 */
public class EasyApplication extends Application implements AMapLocationListener {
    public static File filename, filename1;
    public AMapLocationClient mlocationClient;
    @Override
    public void onCreate() {
        super.onCreate();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filename = new File(getApplicationContext().getExternalCacheDir().getPath()+"easy_images1"); //	storage/emulated/0/Android/data/com.qts.customer/cache
            filename1 = new File(getApplicationContext().getExternalCacheDir().getPath()+"easy_images2");
        } else {
            filename = new File(getApplicationContext().getCacheDir().getPath()+ "easy_images1"); //  data/data/Cachescustomer_images
            filename1 = new File(getApplicationContext().getCacheDir().getPath()+ "easy_images2");// data/data/Cachescustomer_images1
        }
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new UsingFreqLimitedMemoryCache(15 * 1024 * 1024))
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(
                        new UnlimitedDiscCache(filename, filename1,
                                new Md5FileNameGenerator()))
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        mlocationClient = new AMapLocationClient(this);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
                BaseUtil.setLat(getApplicationContext(), amapLocation.getLatitude()+"");
                BaseUtil.setLng(getApplicationContext(), amapLocation.getLongitude()+"");
                mlocationClient.stopLocation();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
                mlocationClient.stopLocation();
            }
        }
    }

}
