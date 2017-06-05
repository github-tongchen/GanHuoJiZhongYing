package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.FragmentPageAdapter;
import com.tongchen.ganhuojizhongying.fragment.GanHuoFragment;
import com.tongchen.ganhuojizhongying.gson.GanHuo;
import com.tongchen.ganhuojizhongying.util.DateUtil;
import com.tongchen.ganhuojizhongying.util.HttpUtil;
import com.tongchen.ganhuojizhongying.util.UtilityUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    private static final String TAG = "MainActivity";

    private TabLayout tabLyt;
    private ViewPager viewpager;

    private List<GanHuoFragment> mFragments = new ArrayList<>();
    private FragmentPageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dynamicAddView(toolbar, "background", R.color.colorPrimaryDark);

        tabLyt = (TabLayout) findViewById(R.id.tabLyt);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.tab_indicator);

        viewpager=(ViewPager) findViewById(R.id.viewpager);

        initDatas();
    }

    private void initDatas() {
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
                    for (String category : ganHuo.getCategory()) {
                        mFragments.add(new GanHuoFragment());
                        Log.d("response",category);
                    }
                    mAdapter=new FragmentPageAdapter(getSupportFragmentManager(),MainActivity.this,mFragments,ganHuo.getCategory());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewpager.setAdapter(mAdapter);

                            tabLyt.setupWithViewPager(viewpager);
                        }
                    });
                }

            }
        });
    }


}
