package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tongchen.ganhuojizhongying.R;

import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.base.SkinBaseActivity;
import solid.ren.skinlibrary.loader.SkinManager;

public class MainActivity extends SkinBaseActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLyt;
    private TabItem tabItem_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initViews();

    }

    private void initViews() {
        tabLyt = (TabLayout) findViewById(R.id.tabLyt_column);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.colorPrimaryDark);

        //CoordinatorLayout cor=(CoordinatorLayout) findViewById(R.id.coorLyt);
        Toolbar to=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(to);
        dynamicAddView(to, "background", R.color.colorPrimaryDark);

       /* tabLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceSkin();
            }
        });*/
    }

    public void replaceSkin(View view) {
        SkinManager.getInstance().loadSkin("theme.skin", new SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(getApplicationContext(), "正在切换中", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "切换成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        Toast.makeText(getApplicationContext(), "切换失败", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailed: "+errMsg);
                    }

                    @Override
                    public void onProgress(int progress) {

                    }
                }

        );
    }


}
