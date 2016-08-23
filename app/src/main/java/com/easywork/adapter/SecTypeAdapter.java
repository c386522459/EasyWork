package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easywork.Model.SecTagBean;
import com.easywork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class SecTypeAdapter extends BaseAdapter{
    private Context mContext;
    private List<SecTagBean> mList = new ArrayList<>();
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
        ViewHolder mHolder;
        if(view==null){
            mHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_simple_sec,null);
            mHolder.name_tv = (TextView) view.findViewById(R.id.sec_tag_tv);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.name_tv.setText(mList.get(i).getName());
        return view;
    }
    class ViewHolder{
        TextView name_tv;
    }
    public void setData(List<SecTagBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
}
