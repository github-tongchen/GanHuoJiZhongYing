package com.tongchen.ganhuojizhongying.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.model.SkinModel;
import com.tongchen.ganhuojizhongying.util.SPUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean isFirstEnter = (Boolean) SPUtil.get(getApplicationContext(), "isFirstEnter", true);

        if (isFirstEnter) {
            initDataBase();
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
        skinModel.setImgId(R.drawable.skin_preview_mint_green);
        skinModel.setName(getString(R.string.skin_mint_green));
        skinModel.setUsed(1);
        skinModel.save();
    }
}
