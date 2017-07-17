package com.tongchen.ganhuojizhongying.attr;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by TongChen on 2017/6/2.
 * <p>
 * Description:换肤开源库默认支持 textColor 和 background 的换肤，其他属性进行换肤需要自定义属性，
 * 此处在需要换肤时SwipeRefreshLayout的下拉刷新箭头也一起变，因此需要自定义
 */

public class SwipeRefreshLayoutAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof SwipeRefreshLayout) {
            SwipeRefreshLayout swipeLyt = (SwipeRefreshLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinResourcesUtils.getColor(attrValueRefId);
                swipeLyt.setColorSchemeColors(color);
            }
        }
    }

}
