package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.SkinAdapter;
import com.tongchen.ganhuojizhongying.model.SkinModel;

import org.litepal.crud.DataSupport;

public class SkinActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SkinActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        findViews();
    }

    private void findViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        SkinAdapter skinAdapter = new SkinAdapter(DataSupport.findAll(SkinModel.class));
        Log.d("skinModel","2--"+DataSupport.findAll(SkinModel.class).size());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(skinAdapter);
    }


}
