package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.model.SkinModel;
import com.tongchen.ganhuojizhongying.util.ResourceUtil;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.SkinLoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;

/**
 * Created by TongChen on 2017/7/13.
 * <p>
 * Description:
 */

public class SkinAdapter extends RecyclerView.Adapter<SkinAdapter.ViewHolder> {

    private Context mContext;
    private List<SkinModel> mDataList;
    private int mHeight, mWidth;
    private List<ViewHolder> holderList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView previewIv;
        ImageView checkedIv;
        TextView nameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            previewIv = (ImageView) itemView.findViewById(R.id.iv_preview);
            checkedIv = (ImageView) itemView.findViewById(R.id.iv_checked);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public SkinAdapter(List<SkinModel> dataList, int width, int height) {
        this.mDataList = dataList;
        mWidth = width;
        mHeight = height;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_skin, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.previewIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                changeSkin(position, v);
            }
        });

        holderList.add(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SkinModel skinModel = mDataList.get(position);
        LogUtil.d("skinModel", skinModel.getName());

        Glide.with(mContext)
                .load(ResourceUtil.getResourceId(skinModel.getImgId(), "drawable", mContext))
                .override(mWidth, mHeight)
                .into(holder.previewIv);
        if (skinModel.isUsed() == 1) {
            holder.checkedIv.setVisibility(View.VISIBLE);
        } else {
            holder.checkedIv.setVisibility(View.INVISIBLE);
        }
        holder.nameTv.setText(skinModel.getName());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    private void changeSkin(final int position, final View view) {
        SkinModel skinModel = mDataList.get(position);
        final String skinName = skinModel.getSkinName();

        if (TextUtils.equals(skinName, "default")) {
            if (!SkinConfig.isDefaultSkin(mContext)) {
                SkinManager.getInstance().restoreDefaultTheme();
                Snackbar.make(view, "已恢复默认皮肤", Snackbar.LENGTH_SHORT).show();

                resetCheck(position);
                updateSkinTable(skinName);
            }
        } else {
            SkinManager.getInstance().loadSkin(skinName, new SkinLoaderListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onProgress(int progress) {
                        }

                        @Override
                        public void onSuccess() {
                            Snackbar.make(view, "切换成功", Snackbar.LENGTH_SHORT).show();

                            resetCheck(position);
                            updateSkinTable(skinName);
                        }

                        @Override
                        public void onFailed(String errMsg) {
                            Log.d("onFailed", errMsg);
                            Snackbar.make(view, "切换失败", Snackbar.LENGTH_SHORT).show();
                        }
                    }

            );
        }
    }

    private void resetCheck(int position) {
        for (int i = 0; i < holderList.size(); i++) {
            if (i == position) {
                holderList.get(i).checkedIv.setVisibility(View.VISIBLE);
            } else {
                holderList.get(i).checkedIv.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void updateSkinTable(String skinName) {
        SkinModel defaultSkin = new SkinModel();
        defaultSkin.setToDefault("used");
        defaultSkin.updateAll("used=?", "1");

        SkinModel currentSkin = new SkinModel();
        currentSkin.setUsed(1);
        currentSkin.updateAll("skinName=?", skinName);
    }

}
