package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tongchen.ganhuojizhongying.ScreenUtil;
import com.tongchen.ganhuojizhongying.constant.Url;
import com.tongchen.ganhuojizhongying.gson.Result;

import java.util.List;

/**
 * Created by TongChen on 2017/7/11.
 * <p>
 * Description:
 */

public class PicPagerAdapter extends PagerAdapter {


    private Context mContext;
    private List<Result> mDatas;

    public PicPagerAdapter(Context mContext, List<Result> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);

        Glide.with(mContext).load(mDatas.get(position).getUrl() + Url.IMAGE_WIDTH_SUFFIX + ScreenUtil.getDisplayWidth(mContext)).into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
