package com.tongchen.ganhuojizhongying.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.FragmentAdapter;
import com.tongchen.ganhuojizhongying.constant.Category;
import com.tongchen.ganhuojizhongying.fragment.PicFragment;
import com.tongchen.ganhuojizhongying.fragment.TextFragment;

import java.util.ArrayList;
import java.util.List;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class MainActivity extends SkinBaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private List<String> mTitles = new ArrayList<>();

    private TabLayout tabLyt;
    private ViewPager viewpager;
    private DrawerLayout drawerLayout;

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentAdapter mAdapter;
    //  点击2次返回才退出
    private long firstTime = 0;

    private String[] mTitleArray = {"全部", "Android", "iOS", "前端", "拓展资源", "休息视频", "瞎推荐", "App", "福利", "天狗"};

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

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

        dynamicAddView(toolbar, "background", R.color.toolbar_bg);

        tabLyt = (TabLayout) findViewById(R.id.tabLyt);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.dlyt_main);

        FloatingActionButton fltBtn = (FloatingActionButton) findViewById(R.id.fab);
        fltBtn.setOnClickListener(this);
        dynamicAddView(fltBtn, "floatingActionButtonBackgroundTint", R.color.colorPrimary);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        dynamicAddView(navigationView, "navigationViewChecked", R.color.colorPrimary);
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
                PicFragment fragment = PicFragment.newInstance(mTitles.get(i));
                mFragments.add(fragment);
            } else {
                TextFragment fragment = TextFragment.newInstance(mTitles.get(i));
                mFragments.add(fragment);
            }
            i++;
        }
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), MainActivity.this, mFragments, mTitles);
        viewpager.setAdapter(mAdapter);
        viewpager.setOffscreenPageLimit(7);
        tabLyt.setupWithViewPager(viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                int index = viewpager.getCurrentItem();
                if (index < mTitles.size() - 1) {
                    ((TextFragment) mFragments.get(index)).back2Top();
                } else {
                    ((PicFragment) mFragments.get(index)).back2Top();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //  HomeAsUp按钮的id永远都是android.R.id.home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        /*drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });*/


        switch (item.getItemId()) {
            case R.id.nav_menu_user:
                break;
            case R.id.nav_menu_favorite:
                break;
            case R.id.nav_menu_skin:
                SkinActivity.start(this);
                break;
            case R.id.nav_menu_app:
                Toast.makeText(MainActivity.this,"功能暂未开放",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_menu_feedback:
                break;
            case R.id.nav_menu_about:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(this, R.string.sys_exit, Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
        }
    }
}
