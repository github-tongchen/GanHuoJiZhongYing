package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.gson.GanHuo;
import com.tongchen.ganhuojizhongying.util.DateUtil;
import com.tongchen.ganhuojizhongying.util.HttpUtil;
import com.tongchen.ganhuojizhongying.util.UtilityUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    private static final String TAG = "MainActivity";

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

        final TabLayout tabLyt = (TabLayout) findViewById(R.id.tabLyt);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.colorPrimaryDark);

        String address = "http://gank.io/api/day/" + DateUtil.getTodayDate();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                GanHuo ganHuo = UtilityUtil.handleClassifyResponse(responseText);
                if (ganHuo != null) {
                    for( String category : ganHuo.getCategory()) {
                        TabItem tab=new TabItem(MainActivity.this);
                    }
                }

            }
        });
    }


}
