package com.easywork.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class PictureAdapter extends BaseAdapter{
    private Context mContext;
    private List<Object> mList = new ArrayList<>();
    public PictureAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
    public void setDate(List<Object> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public void addData(List<Object> mList){
        this.mList.add(mList);
        notifyDataSetChanged();
    }
}
