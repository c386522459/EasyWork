package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easywork.Model.NewsBean;
import com.easywork.R;
import com.easywork.util.BaseTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
public class NewsAdapter extends BaseAdapter{
    private List<NewsBean> mList = new ArrayList<>();
    private Context mContext;
    public void setData(List<NewsBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public void addData(List<NewsBean> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public NewsAdapter(Context mContext){
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
    public View getView(int position, View conventView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if(conventView==null){
            conventView = LayoutInflater.from(mContext).inflate(R.layout.adapter_news,null);
            mHolder = new ViewHolder();
            mHolder.from_time = (TextView) conventView.findViewById(R.id.from_time);
            mHolder.news_iv = (ImageView) conventView.findViewById(R.id.news_iv);
            mHolder.news_title = (TextView) conventView.findViewById(R.id.news_name);
            mHolder.news_iv.setAdjustViewBounds(true);
            conventView.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) conventView.getTag();
        }
        NewsBean mBean = mList.get(position);
        if(!BaseTools.isValueEmpty(mBean.getImgurl())){
            mHolder.news_iv.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(mBean.getImgurl(),mHolder.news_iv);
        }else{
            mHolder.news_iv.setVisibility(View.GONE);
        }
        mHolder.news_title.setText(mBean.getTitle());
        mHolder.from_time.setText(mBean.getResource()+"  "+mBean.getPubtime());
        return conventView;
    }
    class ViewHolder{
        TextView news_title;
        TextView from_time;
        ImageView news_iv;
    }
}
