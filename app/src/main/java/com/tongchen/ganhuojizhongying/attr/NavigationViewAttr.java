package com.tongchen.ganhuojizhongying.attr;

import android.content.res.ColorStateList;
import android.support.design.widget.NavigationView;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by TongChen on 2017/6/2.
 * <p>
 * Description:换肤开源库默认支持 textColor 和 background 的换肤，其他属性进行换肤需要自定义属性，
 * 此处在需要换肤时NavigationView的menu中Item的颜色也一起变，因此需要自定义
 */

public class NavigationViewAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof NavigationView) {
            NavigationView navigationView = (NavigationView) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinResourcesUtils.getColor(attrValueRefId);

                //  设置NavigationView的Menu中Item的颜色，这里的 “ - ” 不能掉
                int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
                int[] colors = new int[]{SkinResourcesUtils.getColor(android.R.color.secondary_text_light), color};
                ColorStateList csl = new ColorStateList(states, colors);

                navigationView.setItemTextColor(csl);
                navigationView.setItemIconTintList(csl);
            }
        }
    }

}
