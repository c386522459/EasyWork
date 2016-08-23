package com.easywork.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easywork.Model.SimpleJobBean;
import com.easywork.Model.SimpleJobBean;
import com.easywork.R;
import com.easywork.util.BaseTools;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class OrderAdapter extends BaseAdapter{
    private Context context;
    private List<SimpleJobBean> mList = new ArrayList<>();
    private LinearLayout.LayoutParams imgParams ;
    public OrderAdapter(Context context){
        this.context = context;
        int  width = (BaseTools.getScreenWidth(context)-50)/3;
        int  height = width/4*3;
        imgParams = new LinearLayout.LayoutParams(width,height);
        imgParams.setMargins(5,5,5,5);
    }
    public void clear(){
        mList.clear();notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }
    public long getOderId(int i){
        if(!BaseTools.isValueEmpty(mList)){
            return mList.get(i).getOrderid();
        }return 0;
    }
    @Override
    public long getItemId(int i) {
        if(!BaseTools.isValueEmpty(mList)){
            return mList.get(i).getJobid();
        }
        return i;
    }

    @Override
    public View getView(int i, View convent, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if(convent==null){
            convent = LayoutInflater.from(context).inflate(R.layout.adapter_job_list,null);
            mHolder = new ViewHolder();
            mHolder.name_tv = (TextView) convent.findViewById(R.id.name_tv);
            mHolder.salary_tv = (TextView) convent.findViewById(R.id.salay_tv);
            mHolder.detail_tv = (TextView) convent.findViewById(R.id.detail_tv);
            mHolder.image_ll = (LinearLayout) convent.findViewById(R.id.image_ll);

            convent.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) convent.getTag();
        }
        SimpleJobBean simpleJobBean = mList.get(i);
        mHolder.name_tv.setText("["+simpleJobBean.getKind()+"]"+simpleJobBean.getName()+"");
        mHolder.salary_tv.setText(simpleJobBean.getDiscount()+" "+simpleJobBean.getDiscountunit());
        mHolder.detail_tv.setText(simpleJobBean.getDescribe()+"");
        mHolder.image_ll.removeAllViewsInLayout();
        mHolder.image_ll.setVisibility(View.GONE);
        if(!BaseTools.isValueEmpty(simpleJobBean.getImgurl())){
            String[] strings = simpleJobBean.getImgurl().split(",");
            int inde=0;
            for(String s:strings){
                if(BaseTools.isValueEmpty(s)||s.equalsIgnoreCase("null")){
                    continue;
                }
                inde++;
                if(inde==5){
                    break;
                }
                addImage(mHolder.image_ll,s);
            }
            if(inde==0){
                mHolder.image_ll.setVisibility(View.GONE);
            }else{
                mHolder.image_ll.setVisibility(View.VISIBLE);
            }
        }
        return convent;
    }
    class ViewHolder{
        TextView name_tv;
        TextView salary_tv;
        TextView detail_tv;
        LinearLayout image_ll;

    }
    public void setmList(List<SimpleJobBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public void addList(List<SimpleJobBean> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }
    public void addImage(final LinearLayout parent, String imgUrl){
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(imgParams);
        imageView.setAdjustViewBounds(true);
        if(!imgUrl.startsWith("http")){
            imgUrl = "http://7xt788.com1.z0.glb.clouddn.com/"+imgUrl;
            imgUrl= imgUrl.replace(";","");
        }
        Log.e("ima",imgUrl);
        ImageLoader.getInstance().displayImage(imgUrl, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                ((ImageView)view).setScaleType(ImageView.ScaleType.CENTER_CROP);
                parent.addView(view);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

    }
    public void clearData(){
        this.mList.clear();
        notifyDataSetChanged();
    }
}
