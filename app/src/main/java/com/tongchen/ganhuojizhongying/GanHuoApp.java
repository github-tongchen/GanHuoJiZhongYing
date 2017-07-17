package com.tongchen.ganhuojizhongying;

import com.tongchen.ganhuojizhongying.attr.FloatingActionButtonAttr;
import com.tongchen.ganhuojizhongying.attr.NavigationViewAttr;
import com.tongchen.ganhuojizhongying.attr.SwipeRefreshLayoutAttr;

import org.litepal.LitePal;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.base.SkinBaseApplication;

/**
 * Created by TongChen on 2017/6/2.
 * <p>
 * Description:
 */

public class GanHuoApp extends SkinBaseApplication {

    private static GanHuoApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //  初始化LitePal
        LitePal.initialize(this);


        SkinConfig.setCanChangeStatusColor(true);
        //  使FloatingActionButton支持换肤
        SkinConfig.addSupportAttr("floatingActionButtonBackgroundTint", new FloatingActionButtonAttr());
        //  使SwipeRefreshLayout支持换肤
        SkinConfig.addSupportAttr("swipeRefreshLayoutSchemeResources", new SwipeRefreshLayoutAttr());
        //  使NavigationView的MenuItem支持换肤
        SkinConfig.addSupportAttr("navigationViewChecked", new NavigationViewAttr());
    }

    public static GanHuoApp getInstance() {
        return instance;
    }
}
