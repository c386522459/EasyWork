package com.easywork.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.easywork.R;

/**
 * Created by cc on 2016/7/23 0023.
 */
public class SiHuiCarouselAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object o) {
//        container.removeViewAt(position);
    }

    // 设置ViewPager指定位置要显示的view
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView im = new ImageView(container.getContext());
        im.setScaleType(ImageView.ScaleType.FIT_XY);
        position = (position%4);
        int id;
        if (position==0){
            id = R.drawable.content_banner01;
        }else if(position==1){
            id = R.drawable.content_banner02;
        }else if(position==2){
            id = R.drawable.content_banner3;
        }else{
            id = R.drawable.content_banner4;
        }
        im.setImageResource(id);
        container.addView(im);
        return im;
    }

}
