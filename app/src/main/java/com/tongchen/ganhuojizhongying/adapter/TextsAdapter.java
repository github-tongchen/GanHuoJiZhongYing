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
import com.tongchen.ganhuojizhongying.activity.TextActivity;
import com.tongchen.ganhuojizhongying.constant.LoadingType;
import com.tongchen.ganhuojizhongying.constant.Url;
import com.tongchen.ganhuojizhongying.gson.Result;

import java.util.List;

/**
 * Created by TongChen on 2017/6/6.
 * <p>
 * Description:
 */

public class TextsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private Context mContext;
    private List<Result> mDataList;
    private int loadStatus = 0;

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView previewIv;
        TextView descTv, dateTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            previewIv = (ImageView) itemView.findViewById(R.id.iv_preview);
            descTv = (TextView) itemView.findViewById(R.id.tv_desc);
            dateTv = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rLyt;
        TextView tipTv;

        public FootViewHolder(View itemView) {
            super(itemView);
            rLyt = (RelativeLayout) itemView;
            tipTv = (TextView) rLyt.findViewById(R.id.tv_tip);
        }
    }

    public TextsAdapter(List<Result> dataList) {
        mDataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_text, parent, false);
            final ItemViewHolder itemViewHolder = new ItemViewHolder(view);

            itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = itemViewHolder.getAdapterPosition();
                    Result result = mDataList.get(position);
                    TextActivity.start(mContext, result.getUrl(),result.getDesc());
                }
            });
            return itemViewHolder;

        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_item_text_footer, parent, false);
            final FootViewHolder footViewHolder = new FootViewHolder(view);

            footViewHolder.rLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = footViewHolder.getAdapterPosition();
                    Result result = mDataList.get(position);
                    Toast.makeText(mContext, result.getDesc(), Toast.LENGTH_SHORT).show();
                }
            });
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            Result result = mDataList.get(position);
            if (result.getImages() != null) {
                Glide.with(mContext).load(result.getImages().get(0) + Url.IMAGE_HEIGHT_SUFFIX + itemViewHolder.previewIv.getHeight()).into(itemViewHolder.previewIv);
            } else {
                itemViewHolder.previewIv.setVisibility(View.GONE);
            }
            itemViewHolder.descTv.setText(result.getDesc());
            itemViewHolder.dateTv.setText(result.getPublishedAt());

        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (loadStatus) {
                case LoadingType.TYPE_MORE:
                    footViewHolder.tipTv.setText(R.string.tv_tip);
                    break;
                case LoadingType.TYPE_LOADING:
                    footViewHolder.tipTv.setText(R.string.tv_tip_loading);
                    loadStatus = LoadingType.TYPE_LOADING;
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        //  由于添加了footer所以返回的值都要加1个
        if (mDataList == null || mDataList.size() == 0) {
            return 1;
        } else {
            return mDataList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        /**
         *  如果当前位置再加1为总长度。就返回footer，否则返回正常的item
         *
         *  假如list.size == 6 。则有数据的item最大position为5，footer的position为6
         *  getItemCount()为7————因为上面加了footer后总数已经 +1
         *  当position+1==7时，说明当前已经是footer了，也就是最末尾了
         */
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void changeStatus(int loadingType) {
        loadStatus = loadingType;
    }
}
