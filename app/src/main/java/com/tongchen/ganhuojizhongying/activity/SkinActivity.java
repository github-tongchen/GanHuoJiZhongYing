package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.ScreenUtil;
import com.tongchen.ganhuojizhongying.adapter.SkinAdapter;
import com.tongchen.ganhuojizhongying.model.SkinModel;

import org.litepal.crud.DataSupport;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class SkinActivity extends SkinBaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SkinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        findViews();
    }

    private void findViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dynamicAddView(toolbar, "background", R.color.toolbar_bg);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //  屏幕宽度减去6个间距(左右最2侧算一个间距，每2个预览图之间算2个间距)的宽度再除以3即为3个预览图的平均宽度
        int width = (ScreenUtil.getDisplayWidth(this) - ScreenUtil.dip2px(20) * 6) / 3;
        //  截图的长宽比为1920:1080，宽度乘以比例即可得出高度
        int height = width * 1920 / 1080;

        SkinAdapter skinAdapter = new SkinAdapter(DataSupport.findAll(SkinModel.class), width, height);
        Log.d("skinModel", "2--" + DataSupport.findAll(SkinModel.class).size());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(skinAdapter);
    }


}
