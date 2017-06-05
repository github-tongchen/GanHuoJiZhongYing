package com.tongchen.ganhuojizhongying;

import com.tongchen.ganhuojizhongying.attr.TabLayoutIndicatorAttr;

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

        //  使TabLayout的指示器支持换肤
        SkinConfig.addSupportAttr("tabLayoutIndicator", new TabLayoutIndicatorAttr());
    }

    public static GanHuoApp getInstance() {
        return instance;
    }
}
