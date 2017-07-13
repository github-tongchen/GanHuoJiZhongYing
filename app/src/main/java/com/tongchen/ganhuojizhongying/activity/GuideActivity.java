package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tongchen.ganhuojizhongying.R;

public class GuideActivity extends AppCompatActivity {

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
