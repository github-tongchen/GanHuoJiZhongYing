package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import com.tongchen.ganhuojizhongying.R;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Toolbar to = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(to);
        dynamicAddView(to, "background", R.color.colorPrimaryDark);

        tabLyt = (TabLayout) findViewById(R.id.tabLyt_column);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.colorPrimaryDark);
    }


}
