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
import android.widget.MediaController;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.easywork.Model.SimpleJobBean;
import com.easywork.R;
import com.easywork.util.BaseTools;
import com.easywork.util.BaseUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/18.
 */
public class JobAdapter extends BaseAdapter {
    private Context context;
    private List<SimpleJobBean> mList = new ArrayList<>();
    private LinearLayout.LayoutParams imgParams;
    private LatLng startLatlng;
    private float distance;
    private String distanceString;

    public JobAdapter(Context context) {
        this.context = context;
        int width = (BaseTools.getScreenWidth(context) - 50) / 3;
        int height = width/4*3;
        imgParams = new LinearLayout.LayoutParams(width,height);
        imgParams.setMargins(5, 5, 5, 5);
        if (BaseTools.isValueEmpty(BaseUtil.getLat(context))) {
            startLatlng = null;
        } else {
            startLatlng = new LatLng(Double.parseDouble(BaseUtil.getLat(context)), Double.parseDouble(BaseUtil.getLng(context)));
        }
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
        if (!BaseTools.isValueEmpty(mList)) {
            return mList.get(i).getJobid();
        }
        return i;
    }

    @Override
    public View getView(int i, View convent, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (convent == null) {
            convent = LayoutInflater.from(context).inflate(R.layout.adapter_job_list, null);
            mHolder = new ViewHolder();
            mHolder.name_tv = (TextView) convent.findViewById(R.id.name_tv);
            mHolder.salary_tv = (TextView) convent.findViewById(R.id.salay_tv);
            mHolder.detail_tv = (TextView) convent.findViewById(R.id.detail_tv);
            mHolder.image_ll = (LinearLayout) convent.findViewById(R.id.image_ll);
            mHolder.distance = (TextView) convent.findViewById(R.id.distance);
            convent.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convent.getTag();
        }
        SimpleJobBean simpleJobBean = mList.get(i);
        mHolder.name_tv.setText("[" + simpleJobBean.getKind() + "]" + simpleJobBean.getName() + "");
        mHolder.salary_tv.setText(simpleJobBean.getDiscount() + " " + simpleJobBean.getDiscountunit());
        mHolder.detail_tv.setText(simpleJobBean.getDescribe() + "");
        mHolder.image_ll.removeAllViewsInLayout();
        mHolder.image_ll.setVisibility(View.GONE);
        if(startLatlng==null||BaseTools.isValueEmpty(simpleJobBean.getAddrlatitude()+"")||BaseTools.isValueEmpty(simpleJobBean.getAddrlongitude()+"")){
            mHolder.distance.setVisibility(View.GONE);
        }else{
            mHolder.distance.setVisibility(View.VISIBLE);
            distance = AMapUtils.calculateLineDistance(startLatlng, new LatLng(simpleJobBean.getAddrlatitude(),simpleJobBean.getAddrlongitude()));
            distanceString="";
            if (distance < 50) {
                distanceString="50m内";
            } else {
                if (distance < 1000) {
                    BigDecimal bd = new BigDecimal(distance);
                    bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                    distanceString = bd+"m";
                } else {
                    distance = distance / 1000;
                    BigDecimal bd = new BigDecimal(distance);
                    bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
                    distanceString = bd+"Km";
                }
            }
            mHolder.distance.setText(distanceString);
        }
        if (!BaseTools.isValueEmpty(simpleJobBean.getImgurl())) {
            String[] strings = simpleJobBean.getImgurl().split(",");
            int inde = 0;
            for (String s : strings) {
                if (BaseTools.isValueEmpty(s) || s.equalsIgnoreCase("null")) {
                    continue;
                }
                inde++;
                if (inde == 4) {
                    break;
                }
                addImage(mHolder.image_ll, s);
            }
            String name = "";
            if (inde == 0) {
                mHolder.image_ll.setVisibility(View.GONE);
            } else {
                name = simpleJobBean.getParentkind();
                mHolder.image_ll.setVisibility(View.VISIBLE);
            }
            if (strings.length==1){
                addLocalImage(mHolder.image_ll,name);
                addLocalImage(mHolder.image_ll,name);
            }
            if (strings.length==2){
                addLocalImage(mHolder.image_ll,name);
            }
        }
        return convent;
    }

    class ViewHolder {
        TextView name_tv;
        TextView salary_tv;
        TextView detail_tv, distance;
        LinearLayout image_ll;

    }

    public void setmList(List<SimpleJobBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void addList(List<SimpleJobBean> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public void addImage(final LinearLayout parent, String imgUrl) {
        final ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(imgParams);
        imageView.setAdjustViewBounds(true);
        if (!imgUrl.startsWith("http")) {
            imgUrl = "http://7xt788.com1.z0.glb.clouddn.com/" + imgUrl;
            imgUrl = imgUrl.replace(";", "");
        }
        Log.e("ima", imgUrl);
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
    public void addLocalImage(final LinearLayout parent, String name) {
        final ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(imgParams);
        imageView.setAdjustViewBounds(true);
        int id = getId(name);
        ImageLoader.getInstance().displayImage("drawable://" + id, imageView, new ImageLoadingListener() {
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
    public void setNoData(){
        this.mList.clear();
        notifyDataSetChanged();
    }

    public int getId(String name){
        System.out.println(name);
        if (name.equals("建筑")){
            return R.drawable.jianzhu;
        }
        if (name.equals("装修")){
            return R.drawable.zhuangxiu;
        }
        if (name.equals("轻工厂")){
            return R.drawable.qinggongchang;
        }
        if (name.equals("找车")){
            return R.drawable.zhaoche;
        }
        if (name.equals("家政")){
            return R.drawable.jiazheng;
        }
        if (name.equals("服务")){
            return R.drawable.fuwu;
        }
        if (name.equals("房产租售")){
            return R.drawable.chuzu;
        }
        if (name.equals("二手车")){
            return R.drawable.ershouche;
        }
        if (name.equals("快商店")){
            return R.drawable.kuaishangdian;
        }
        if (name.equals("娱乐")){
            return R.drawable.yule;
        }
        if (name.equals("推广")){
            return R.drawable.tuiguang;
        }
        if (name.equals("快婚")){
            return R.drawable.kuaihun;
        }
        return R.drawable.item_default;
    }
}








