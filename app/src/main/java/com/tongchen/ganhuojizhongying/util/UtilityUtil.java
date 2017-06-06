package com.tongchen.ganhuojizhongying.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tongchen.ganhuojizhongying.gson.Android;

/**
 * Created by TongChen on 2017/6/5.
 * <p>
 * Description:解析和处理JSON
 */

public class UtilityUtil {

    public static Android handleAndroidResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            return new Gson().fromJson(response, Android.class);
        }
        return null;
    }

}
