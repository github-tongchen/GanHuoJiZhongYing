package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;

import com.tongchen.ganhuojizhongying.GanHuoApp;
import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.model.SkinModel;
import com.tongchen.ganhuojizhongying.util.SPUtil;

import java.util.Timer;
import java.util.TimerTask;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class SplashActivity extends SkinBaseActivity {

    public static final String FIRST_ENTER = "isFirstEnter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean isFirstEnter = (Boolean) SPUtil.get(getApplicationContext(), FIRST_ENTER, true);

        if (isFirstEnter) {
            initDataBase();
            SPUtil.putAndApply(GanHuoApp.getInstance(), FIRST_ENTER, false);

            GuideActivity.start(this);
            finish();
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    MainActivity.start(SplashActivity.this);
                    SplashActivity.this.finish();
                }
            }, 1000);//延时1s执行

        }
    }

    private void initDataBase() {
        SkinModel skinModel = new SkinModel();
        skinModel.setImgId(R.drawable.skin_preview_bohelv);
        skinModel.setName(getString(R.string.skin_bohelv));
        skinModel.setSkinName("default");
        skinModel.setUsed(1);
        skinModel.save();

        SkinModel skinModel1 = new SkinModel();
        skinModel1.setImgId(R.drawable.skin_preview_furonghong);
        skinModel1.setName(getString(R.string.skin_furonghong));
        skinModel1.setSkinName("fu_rong_hong.skin");
        skinModel1.setUsed(0);
        skinModel1.save();

        SkinModel skinModel2 = new SkinModel();
        skinModel2.setImgId(R.drawable.skin_preview_bingxuelan);
        skinModel2.setName(getString(R.string.skin_bingxuelan));
        skinModel2.setSkinName("bing_xue_lan.skin");
        skinModel2.setUsed(0);
        skinModel2.save();

        SkinModel skinModel3 = new SkinModel();
        skinModel3.setImgId(R.drawable.skin_preview_ganlanlv);
        skinModel3.setName(getString(R.string.skin_ganlanlv));
        skinModel3.setSkinName("gan_lan_lv.skin");
        skinModel3.setUsed(0);
        skinModel3.save();
    }
}
