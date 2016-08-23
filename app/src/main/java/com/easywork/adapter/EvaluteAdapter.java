package com.easywork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easywork.Model.ComplianBean;
import com.easywork.Model.InfoBean;
import com.easywork.R;
import com.easywork.ui.RoundImageView;
import com.easywork.util.BaseTools;
import com.easywork.util.BaseUtil;
import com.easywork.util.Constrants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class EvaluteAdapter  extends BaseAdapter{
    private List<ComplianBean> mDatas = new ArrayList<>();
    private Context mContext;
    public EvaluteAdapter(Context mContext){
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
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_evalute,null);
            mHolder.evaluter_iv = (RoundImageView) view.findViewById(R.id.evaluter_iv);
            mHolder.time_tv = (TextView) view.findViewById(R.id.time_tv);
            mHolder.name_tv = (TextView) view.findViewById(R.id.name_tv);
            mHolder.evalution_tv = (TextView) view.findViewById(R.id.evalution_tv);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        ComplianBean bean = mDatas.get(i);
        InfoBean infoBean = bean.getFromuser();
        mHolder.evalution_tv.setText(bean.getContent());
        mHolder.time_tv.setText(bean.getInputtime());
        if(infoBean!=null){
            if(BaseTools.isValueEmpty(infoBean.getHeadurl())){
                mHolder.evaluter_iv.setImageResource(R.drawable.icon_userpic_small);
            }else{
                String url =infoBean.getHeadurl();
                if(!infoBean.getHeadurl().startsWith("http")){
                    url = BaseUtil.getQniuUrl(mContext)+"/"+url;
                    url = url.replace(";","");
                }
                ImageLoader.getInstance().displayImage(url,mHolder.evaluter_iv);
            }
            mHolder.name_tv.setText(infoBean.getNickname());
        }
        return view;
    }
    class ViewHolder{
        RoundImageView evaluter_iv;
        TextView time_tv;
        TextView name_tv;
        TextView evalution_tv;
    }
    public void setmDatas(List<ComplianBean> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }
    public void addDatas(List<ComplianBean> mDatas){
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }
    public void addData(ComplianBean data){
        this.mDatas.add(0,data);
        notifyDataSetChanged();
    }
}
