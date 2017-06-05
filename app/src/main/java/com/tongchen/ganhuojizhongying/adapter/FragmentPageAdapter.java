package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tongchen.ganhuojizhongying.fragment.GanHuoFragment;

import java.util.List;

/**
 * Created by TongChen on 2017/6/5.
 * <p>
 * Description:
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<GanHuoFragment> mFragments;
    private List<String> mTitles;

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentPageAdapter(FragmentManager fm, Context context, List<GanHuoFragment> fragments, List<String> titles) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
