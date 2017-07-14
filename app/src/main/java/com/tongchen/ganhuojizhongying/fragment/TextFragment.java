package com.tongchen.ganhuojizhongying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.TextsAdapter;
import com.tongchen.ganhuojizhongying.constant.LoadingType;
import com.tongchen.ganhuojizhongying.constant.Url;
import com.tongchen.ganhuojizhongying.gson.GanHuo;
import com.tongchen.ganhuojizhongying.gson.Result;
import com.tongchen.ganhuojizhongying.util.HttpUtil;
import com.tongchen.ganhuojizhongying.util.UtilityUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import solid.ren.skinlibrary.base.SkinBaseFragment;

public class TextFragment extends SkinBaseFragment {

    private static final String ARG_TITLE = "title";
    //  当前所处Tab的分类
    private String mTitle;
    private int loadedPageNum;
    private int lastVisiblePosition;


    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private List<Result> resultList = new ArrayList<>();

    private TextsAdapter newsAdapter;

    public TextFragment() {

    }

    public static TextFragment newInstance(String title) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_text, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViews();
        loadContent(LoadingType.TYPE_INIT);
    }

    private void findViews() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_lyt);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadContent(LoadingType.TYPE_REFRESH);
                newsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisiblePosition + 1 == newsAdapter.getItemCount()) {
                    loadContent(LoadingType.TYPE_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void loadContent(final int loadingType) {
        String address;
        switch (loadingType) {
            case LoadingType.TYPE_INIT:
            case LoadingType.TYPE_REFRESH:
                loadedPageNum = 1;
                address = Url.GANHUO_DATA + mTitle + "/10/" + loadedPageNum;
                break;
            case LoadingType.TYPE_MORE:
                loadedPageNum++;
                address = Url.GANHUO_DATA + mTitle + "/10/" + loadedPageNum;
                break;
            default:
                address = Url.GANHUO_DATA + mTitle + "/10/1";
                break;
        }
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Snackbar.make(view, "加载失败，请重试", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final GanHuo ganHuo = UtilityUtil.handleGanHuoResponse(responseText);
                if (ganHuo != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (loadingType) {
                                case LoadingType.TYPE_INIT:
                                    resultList.clear();
                                    resultList.addAll(ganHuo.getResults());
                                    newsAdapter = new TextsAdapter(resultList);
                                    recyclerView.setAdapter(newsAdapter);
                                    break;
                                case LoadingType.TYPE_REFRESH:
                                    resultList.clear();
                                    resultList.addAll(ganHuo.getResults());
                                    newsAdapter.notifyDataSetChanged();
                                    swipeRefreshLayout.setRefreshing(false);
                                    break;
                                case LoadingType.TYPE_MORE:
                                    resultList.addAll(ganHuo.getResults());
                                    newsAdapter.notifyDataSetChanged();
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }

    //  返回到顶部
    public void back2Top() {
        recyclerView.smoothScrollToPosition(0);
    }

}
