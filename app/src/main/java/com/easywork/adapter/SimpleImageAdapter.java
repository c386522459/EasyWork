package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.easywork.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SimpleImageAdapter extends BaseAdapter{
    private List<String> mImgs = new ArrayList<>();
    private Context mContext;
    public SimpleImageAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mImgs.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if(view==null){
            view  = LayoutInflater.from(mContext).inflate(R.layout.adapter_simple_image,null);
            mHolder = new ViewHolder();
            mHolder.simple_iv = (ImageView) view.findViewById(R.id.simple_iv);
            mHolder.simple_iv.setAdjustViewBounds(true);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(mImgs.get(i),mHolder.simple_iv);
        return view;
    }
    class ViewHolder{
        ImageView simple_iv;
    }
    public void setData(List<String> mImgs){
        this.mImgs = mImgs;
       // notifyDataSetChanged();
    }
    public void addData(List<String> mImgs){
        this.mImgs.addAll(mImgs);
        notifyDataSetChanged();
    }
}
