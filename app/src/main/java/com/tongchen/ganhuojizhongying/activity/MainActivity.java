package com.tongchen.ganhuojizhongying.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.FragmentPageAdapter;
import com.tongchen.ganhuojizhongying.constant.Category;
import com.tongchen.ganhuojizhongying.fragment.NewsFragment;
import com.tongchen.ganhuojizhongying.fragment.PicsFragment;

import java.util.ArrayList;
import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity {

    private List<String> mTitles = new ArrayList<>();

    private TabLayout tabLyt;
    private ViewPager viewpager;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPageAdapter mAdapter;

    private String[] mTitleArray = {"全部", "Android", "iOS", "前端", "拓展资源", "休息视频", "瞎推荐", "App", "福利", "天狗"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        initViews();
        initCategories();
        initDatas();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "拒绝权限程序将无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dynamicAddView(toolbar, "background", R.color.colorPrimary);

        tabLyt = (TabLayout) findViewById(R.id.tabLyt);
        //  使TabLayout的指示器支持换肤
        dynamicAddView(tabLyt, "tabLayoutIndicator", R.color.tab_indicator);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initCategories() {
        //mTitles.add(mTitleArray[Category.ALL]);
        mTitles.add(mTitleArray[Category.ANDROID]);
        mTitles.add(mTitleArray[Category.IOS]);
        mTitles.add(mTitleArray[Category.FONT_END]);
        mTitles.add(mTitleArray[Category.RESOURCE]);
        mTitles.add(mTitleArray[Category.RECOMMEND]);
        mTitles.add(mTitleArray[Category.VIDEO]);
        mTitles.add(mTitleArray[Category.APP]);
        mTitles.add(mTitleArray[Category.WELFARE]);
    }

    private void initDatas() {
        int i = 0;
        while (i < mTitles.size()) {
            if (i == mTitles.size() - 1) {
                PicsFragment fragment = PicsFragment.newInstance(mTitles.get(i));
                mFragments.add(fragment);
            } else {
                NewsFragment fragment = NewsFragment.newInstance(mTitles.get(i));
                mFragments.add(fragment);
            }
            i++;
        }
        mAdapter = new FragmentPageAdapter(getSupportFragmentManager(), MainActivity.this, mFragments, mTitles);
        viewpager.setAdapter(mAdapter);
        tabLyt.setupWithViewPager(viewpager);
    }

}
