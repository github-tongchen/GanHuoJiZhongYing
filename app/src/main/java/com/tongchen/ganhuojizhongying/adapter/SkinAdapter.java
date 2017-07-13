package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.model.SkinModel;

import org.litepal.util.LogUtil;

import java.util.List;

/**
 * Created by TongChen on 2017/7/13.
 * <p>
 * Description:
 */

public class SkinAdapter extends RecyclerView.Adapter<SkinAdapter.ViewHolder> {

    private Context mContext;
    private List<SkinModel> mDataList;

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

    public SkinAdapter(List<SkinModel> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_skin, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.previewIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SkinModel skinModel = mDataList.get(position);
        LogUtil.d("skinModel",skinModel.getName());

        Glide.with(mContext).load(skinModel.getImgId()).into(holder.previewIv);
        if (skinModel.isUsed()) {
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
}
