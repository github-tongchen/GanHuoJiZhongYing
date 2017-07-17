package com.tongchen.ganhuojizhongying.attr;

import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by TongChen on 2017/6/2.
 * <p>
 * Description:换肤开源库默认支持 textColor 和 background 的换肤，其他属性进行换肤需要自定义属性，
 * 此处在需要换肤时FloatingActionButton的背景颜色也跟着变化，因此需要自定义
 */

public class FloatingActionButtonAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof FloatingActionButton) {
            FloatingActionButton flb = (FloatingActionButton) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                int color = SkinResourcesUtils.getColor(attrValueRefId);
                //  用代码设置BackgroundTint
                flb.setBackgroundTintList(ColorStateList.valueOf(color));
            }
        }
    }

}
