package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.FragmentPageAdapter;
import com.tongchen.ganhuojizhongying.constant.Category;
import com.tongchen.ganhuojizhongying.fragment.AndroidFragment;

import java.util.ArrayList;
import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    private static final String TAG = "MainActivity";

    private List<String> mTitles = new ArrayList<>();

    private TabLayout tabLyt;
    private ViewPager viewpager;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initCategories();
        initDatas();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dynamicAddView(toolbar, "background", R.color.colorPrimaryDark);

        tabLyt = (TabLayout) findViewById(R.id.tabLyt);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.tab_indicator);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initCategories() {
        mTitles.add(Category.ALL);
        mTitles.add(Category.ANDROID);
        mTitles.add(Category.IOS);
        mTitles.add(Category.FONT_END);
        mTitles.add(Category.RESOURCE);
        mTitles.add(Category.RECOMMEND);
        mTitles.add(Category.VIDEO);
        mTitles.add(Category.APP);
        mTitles.add(Category.WELFARE);
    }

    private void initDatas() {
        int i = 0;
        while (i < mTitles.size()) {
            mFragments.add(new AndroidFragment());
            i++;
        }
        mAdapter = new FragmentPageAdapter(getSupportFragmentManager(), MainActivity.this, mFragments, mTitles);
        viewpager.setAdapter(mAdapter);
        tabLyt.setupWithViewPager(viewpager);
        /*HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                GanHuo ganHuo = UtilityUtil.handleClassifyResponse(responseText);
                if (ganHuo != null) {
                    mAdapter = new FragmentPageAdapter(getSupportFragmentManager(), MainActivity.this, mFragments);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewpager.setAdapter(mAdapter);

                            tabLyt.setupWithViewPager(viewpager);
                        }
                    });
                }

            }
        });*/
    }


}
