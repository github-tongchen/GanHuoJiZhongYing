package com.tongchen.ganhuojizhongying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.PicsAdapter;
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

public class PicsFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    //  当前所处Tab的分类
    private String mTitle;

    private int lastVisiblePosition;
    //  已经加载的页数
    private int loadedPageNum = 1;

    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private PicsAdapter picsAdapter;
    private List<Result> resultList = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    public PicsFragment() {

    }

    public static PicsFragment newInstance(String title) {
        PicsFragment fragment = new PicsFragment();
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
        view = inflater.inflate(R.layout.fragment_pics, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
        loadContent(LoadingType.TYPE_INIT);
    }

    private void initViews() {
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
                picsAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisiblePosition + 1 == picsAdapter.getItemCount()) {
                    loadContent(LoadingType.TYPE_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiblePosition = gridLayoutManager.findLastVisibleItemPosition();
                Log.d("Nsize","3---"+lastVisiblePosition);
            }
        });

    }


    //  根据加载类型发送不同的
    private void loadContent(final int loadingType) {
        String address;
        switch (loadingType) {
            case LoadingType.TYPE_INIT:
            case LoadingType.TYPE_REFRESH:
                loadedPageNum = 1;
                address = Url.GANHUO_DATA + mTitle + "/20/" + loadedPageNum;
                break;
            case LoadingType.TYPE_MORE:
                loadedPageNum++;
                address = Url.GANHUO_DATA + mTitle + "/20/" + loadedPageNum;
                break;
            default:
                address = Url.GANHUO_DATA + mTitle + "/20/1";
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
                String responseText = response.body().string();
                final GanHuo ganHuo = UtilityUtil.handleGanHuoResponse(responseText);
                if (ganHuo != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            switch (loadingType) {
                                case LoadingType.TYPE_INIT:
                                    resultList.clear();
                                    resultList.addAll(ganHuo.getResults());
                                    picsAdapter = new PicsAdapter(resultList);
                                    recyclerView.setAdapter(picsAdapter);
                                    break;
                                case LoadingType.TYPE_REFRESH:
                                    resultList.clear();
                                    resultList.addAll(ganHuo.getResults());
                                    picsAdapter.notifyDataSetChanged();
                                    swipeRefreshLayout.setRefreshing(false);
                                    break;
                                case LoadingType.TYPE_MORE:
                                    for (Result result : ganHuo.getResults()) {
                                        resultList.add(result);
                                    }
                                    picsAdapter.notifyDataSetChanged();
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }
}
