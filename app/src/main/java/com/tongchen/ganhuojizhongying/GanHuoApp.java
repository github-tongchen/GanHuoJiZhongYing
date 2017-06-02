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

    @Override
    public void onCreate() {
        super.onCreate();

        SkinConfig.addSupportAttr("tabLayoutIndicator", new TabLayoutIndicatorAttr());
    }
}
