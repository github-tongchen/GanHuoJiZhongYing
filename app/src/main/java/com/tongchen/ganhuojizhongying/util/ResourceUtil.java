package com.tongchen.ganhuojizhongying.util;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by TongChen on 2017/7/19.
 * <p>
 * Description:
 */

public class ResourceUtil {

    /**
     * 根据数据库里存储的资源的名字(R.xxx.name的name——String类型)获取图片的Id(R.xxx.name所对应的Id——int类型)
     *
     * @param resId   上面的String类型的name
     * @param resType 上面的xxx的值，如“raw”、“drawable”、“color”等
     */
    public static int getResourceId(String resId, String resType, Context context) {
        Resources res = context.getResources();
        return res.getIdentifier(resId, resType, context.getApplicationContext().getPackageName());
    }
}
