package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.ScreenUtil;
import com.tongchen.ganhuojizhongying.gson.Result;

import java.util.List;

/**
 * Created by TongChen on 2017/6/7.
 * <p>
 * Description:
 */

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.ViewHolder> {

    private Context mContext;
    private List<Result> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView previewIv;
        TextView whoTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            previewIv = (ImageView) cardView.findViewById(R.id.iv_preview);
            whoTv = (TextView) cardView.findViewById(R.id.tv_who);
        }
    }

    public PicsAdapter(List<Result> dataList) {
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_pics, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        int height= ScreenUtil.getDisplayHeight(mContext)/3;
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        holder.previewIv.setLayoutParams(layoutParams);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Result result = mDataList.get(position);
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = mDataList.get(position);
        Glide.with(mContext).load(result.getUrl()).into(holder.previewIv);
        holder.whoTv.setText(result.getWho());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
