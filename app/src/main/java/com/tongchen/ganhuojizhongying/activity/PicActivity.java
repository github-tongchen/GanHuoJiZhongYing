package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.PicPagerAdapter;
import com.tongchen.ganhuojizhongying.gson.Result;

import java.io.Serializable;
import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class PicActivity extends SkinBaseActivity {

    private int mIndex;
    private String mUrl;
    private List<Result> mDatas;

    public static void start(Context context, int index, String url, List<Result> datas) {
        Intent intent = new Intent(context, PicActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("url", url);
        intent.putExtra("datas", (Serializable) datas);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        mIndex = getIntent().getIntExtra("index", -1);
        mUrl = getIntent().getStringExtra("url");
        mDatas = (List<Result>) (getIntent().getSerializableExtra("datas"));

        findViews();
    }

    private void findViews() {
        ViewPager ImgVp = (ViewPager) findViewById(R.id.view_pager);

        PicPagerAdapter picPagerAdapter = new PicPagerAdapter(PicActivity.this, mDatas);
        ImgVp.setAdapter(picPagerAdapter);
        ImgVp.setOffscreenPageLimit(1);
        ImgVp.setCurrentItem(mIndex);

        //if (!TextUtils.isEmpty(mUrl)) {
        // Glide.with(PicActivity.this).load(mIndex).into(imageView);
        //}
    }
}
