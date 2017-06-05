package com.tongchen.ganhuojizhongying.util;

import android.widget.Toast;

import com.tongchen.ganhuojizhongying.GanHuoApp;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by TongChen on 2017/6/5.
 * <p>
 * Description:皮肤的控制类
 */

public class SkinUtil {

    /**
     * 对皮肤进行处理
     *
     * @param skinName 被选中皮肤的文件名
     */
    public static void handleSkin(String skinName) {
        //  如果当前是默认皮肤
        if (SkinConfig.isDefaultSkin(GanHuoApp.getInstance())) {
            replaceSkin(skinName);
        } else {
            restoreSkin();
        }

    }

    /**
     * 恢复默认皮肤
     */
    private static void restoreSkin() {
        SkinManager.getInstance().restoreDefaultTheme();
    }

    /**
     * 切换到被选中的皮肤
     *
     * @param skinName 被选中皮肤的文件名
     */
    private static void replaceSkin(String skinName) {
        SkinManager.getInstance().loadSkin(skinName, new SkinLoaderListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(GanHuoApp.getInstance(), "正在切换中", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        Toast.makeText(GanHuoApp.getInstance(), "切换成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(String errMsg) {
                        Toast.makeText(GanHuoApp.getInstance(), "切换失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int progress) {

                    }
                }
        );

    }
}
