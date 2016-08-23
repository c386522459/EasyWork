package com.easywork.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easywork.Model.SimpleTypeBean;
import com.easywork.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/19.
 */
public class TypeAdapter extends BaseAdapter{
    private List<SimpleTypeBean> mList = new ArrayList<>();
    private Context mContext;
    private int position=0;
    public TypeAdapter(Context mContext){
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
        ViewHolder mHolder;
        if(view==null){
            mHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_left_type,null);
            mHolder.type_iv = (ImageView) view.findViewById(R.id.type_iv);
            mHolder.name_tv = (TextView) view.findViewById(R.id.type_tv);
            mHolder.item_ll = (LinearLayout) view.findViewById(R.id.item_ll);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        SimpleTypeBean simpleTypeBean = mList.get(i);
        ImageLoader.getInstance().displayImage(simpleTypeBean.getSmallimgurl(),mHolder.type_iv);
        mHolder.name_tv.setText(simpleTypeBean.getName());
        if(position==i){
            mHolder.item_ll.setBackgroundColor(mContext.getResources().getColor(R.color.c_bg));
        }else{
            mHolder.item_ll.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        return view;
    }
    class ViewHolder{
        LinearLayout item_ll;
        ImageView type_iv;
        TextView name_tv;
    }
    public void setDada(List<SimpleTypeBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public void setbg(int position){
        this.position = position;
        notifyDataSetChanged();
    }
}
