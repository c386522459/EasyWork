package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easywork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/21.
 */
public class SimpleStringAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    public SimpleStringAdapter(Context mContext,List<String> mList){
        this.mContext = mContext;
        this.mList = mList;
    }
    public SimpleStringAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        try {
            return mList.get(i);
        }catch (Exception e){
            return "";
        }

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView mTextView;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_simple_text,null);
            mTextView = (TextView) view.findViewById(R.id.simple_text);
            view.setTag(mTextView);
        }else{
            mTextView = (TextView) view.getTag();
        }
        mTextView.setText(mList.get(i));
        return view;
    }
    public void setData(List<String> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
}
