package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tongchen.ganhuojizhongying.R;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class GuideActivity extends SkinBaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViews();

        MainActivity.start(this);
        finish();
    }

    private void findViews() {


    }
}
