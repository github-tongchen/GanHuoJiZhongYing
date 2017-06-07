package com.tongchen.ganhuojizhongying.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchen.ganhuojizhongying.R;
import com.tongchen.ganhuojizhongying.adapter.PicsAdapter;
import com.tongchen.ganhuojizhongying.constant.Url;
import com.tongchen.ganhuojizhongying.gson.GanHuo;
import com.tongchen.ganhuojizhongying.util.HttpUtil;
import com.tongchen.ganhuojizhongying.util.UtilityUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PicsFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    //  当前所处Tab的分类
    private String mTitle;
    
    private View view;
    private RecyclerView recyclerView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_pics, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        initViews();
        loadContent();
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //  解决RecyclerView 瀑布流 item高度不能自适应的问题 ps:快速滑动时图片大小不能正常显示，依然有问题
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

    }

    private void loadContent() {
        String address = Url.GANHUO_DATA + mTitle + "/50/1";
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
                            PicsAdapter picsAdapter = new PicsAdapter(ganHuo.getResults());
                            recyclerView.setAdapter(picsAdapter);
                        }
                    });
                }
            }
        });
    }
}
