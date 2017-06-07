package com.tongchen.ganhuojizhongying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.NewsAdapter;
import com.tongchen.ganhuojizhongying.gson.GanHuo;
import com.tongchen.ganhuojizhongying.util.HttpUtil;
import com.tongchen.ganhuojizhongying.util.UtilityUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsFragment extends Fragment {

    public static final String GANHUO_DATA = "http://gank.io/api/data/";

    private View view;
    private RecyclerView recyclerView;
    //  当前所处Tab的分类
    private String mTitle;

    public NewsFragment() {

    }

    public static NewsFragment newInstance(String title) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_android, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findViews();

        loadContent();
    }

    private void findViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void loadContent() {
        String address = GANHUO_DATA + mTitle + "/10/1";
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
                            NewsAdapter newsAdapter = new NewsAdapter(ganHuo.getResults());
                            recyclerView.setAdapter(newsAdapter);
                        }
                    });
                }
            }
        });
    }


}
