package com.tongchen.ganhuojizhongying.attr;

import android.support.design.widget.TabLayout;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by TongChen on 2017/6/2.
 * <p>
 * Description:换肤开源库默认支持 textColor 和 background 的换肤，其他属性进行换肤需要自定义属性，
 * 此处在需要换肤时TabLayout的指示器颜色也跟着变化，因此需要自定义
 */

public class TabLayoutIndicatorAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TabLayout) {
            TabLayout tablyt = (TabLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinResourcesUtils.getColor(attrValueRefId);
                tablyt.setSelectedTabIndicatorColor(color);
            }
        }
    }

}
