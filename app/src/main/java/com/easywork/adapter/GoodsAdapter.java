package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easywork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
public class GoodsAdapter extends BaseAdapter{
    private List<Object> mDatas = new ArrayList<>();
    private Context mContext;
    public GoodsAdapter(Context mContext){
        super();
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        if(mDatas==null){
            return null;
        }
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if(view==null){
            mHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_goods,null);
            mHolder.goods_iv = (ImageView) view.findViewById(R.id.goods_iv);
            mHolder.title_tv = (TextView) view.findViewById(R.id.title_tv);
            mHolder.need_tv = (TextView) view.findViewById(R.id.need_tv);
            mHolder.address_tv = (TextView) view.findViewById(R.id.address_tv);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }

        return view;
    }
    class ViewHolder{
        ImageView goods_iv;
        TextView title_tv;
        TextView need_tv;
        TextView address_tv;
    }
    public void setmDatas(List<Object> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
    public void addDatas(List<Object> mDatas){
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }
}
