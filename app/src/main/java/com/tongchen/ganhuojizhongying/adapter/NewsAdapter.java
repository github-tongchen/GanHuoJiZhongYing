package com.tongchen.ganhuojizhongying.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.gson.Result;

import java.util.List;

/**
 * Created by TongChen on 2017/6/6.
 * <p>
 * Description:
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context mContext;
    private List<Result> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView previewIv;
        TextView descTv, dateTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            previewIv = (ImageView) itemView.findViewById(R.id.iv_preview);
            descTv = (TextView) itemView.findViewById(R.id.tv_desc);
            dateTv = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    public NewsAdapter(List<Result> dataList) {
        mDataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_news, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Result result = mDataList.get(position);
                Toast.makeText(mContext, result.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result result = mDataList.get(position);
        if(result.getImages() !=null){
            Glide.with(mContext).load(result.getImages().get(0)).into(holder.previewIv);
        }
        holder.descTv.setText(result.getDesc());
        holder.dateTv.setText(result.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
