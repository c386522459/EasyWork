package com.easywork.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easywork.R;

/**
 * Created by Administrator on 2016/5/12.
 */
public class HomeRagPageAdapter extends PagerAdapter{
    private Context mContext;
    private View view1,view2;
    @Override
    public int getCount() {
        return 2;
    }
    public HomeRagPageAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
      if(position==0){
          if(view1==null){
              view1 = LayoutInflater.from(mContext).inflate(R.layout.page_one_layout,null);
          }
          container.addView(view1);
          return view1;
      }else{
          if(view2==null){
              view2 = LayoutInflater.from(mContext).inflate(R.layout.page_sec_layout,null);
          }
          container.addView(view2);
          return view2;
      }
    }
}
