package com.easywork.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.easywork.HomeBossActivity;
import com.easywork.Model.NewsBean;
import com.easywork.R;
import com.easywork.SiHuiEducationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class BannerAdapter extends PagerAdapter{
    private List<NewsBean> homead = new ArrayList<>();
    private Activity activity;
    public BannerAdapter(Activity tempactivity, List<NewsBean> temphomead) {
        this.activity = tempactivity;
        this.homead = temphomead;
    }

    @Override
    public int getCount() {
//        if (!BaseTools.isValueEmpty(homead)) {
//            return homead.size();
//        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object o) {

        // container.removeViewAt(position);

    }

    // 设置ViewPager指定位置要显示的view
    @Override
    public Object instantiateItem(ViewGroup container,int position) {
        final ImageView im = new ImageView(activity);
        im.setScaleType(ImageView.ScaleType.FIT_XY);
        position = (position%4);
        int id;
        if (position==0){
            id = R.drawable.banner1;
        }else if(position==1){
            id = R.drawable.banner4;
        }else if(position==2){
            id = R.drawable.banner3;
        }else{
            id = R.drawable.banner2;
        }
        im.setImageResource(id);
//        if (BaseTools.isValueEmpty(homead)) {
//            im.setImageResource(R.drawable.timg);
//            container.addView(im);
//            return im;
//        }
//        im.setImageResource(R.drawable.timg);
//        if (!BaseTools.isValueEmpty(homead.get(position).getImgurl())) {
//            ImageLoader.getInstance().displayImage(homead.get(position).getImgurl(), im, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String s, View view) {
//
//                }
//
//                @Override
//                public void onLoadingFailed(String s, View view, FailReason failReason) {
//
//                }
//
//                @Override
//                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                    im.setScaleType(ImageView.ScaleType.CENTER);
//                }
//
//                @Override
//                public void onLoadingCancelled(String s, View view) {
//
//                }
//            });
//        }
        if (position==1) {
            im.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent mIntent = new Intent();
                    mIntent.setClass(v.getContext(), SiHuiEducationActivity.class);
                    activity.startActivity(mIntent);
                }
            });
        }
        container.addView(im);
        return im;
    }

}
