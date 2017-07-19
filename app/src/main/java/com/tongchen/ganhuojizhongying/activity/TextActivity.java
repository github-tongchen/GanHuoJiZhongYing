package com.tongchen.ganhuojizhongying.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.tongchen.ganhuojizhongying.R;

import solid.ren.skinlibrary.base.SkinBaseActivity;

public class TextActivity extends SkinBaseActivity {

    private WebView webView;

    public static void start(Context context, String url, String desc) {
        Intent intent = new Intent(context, TextActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("desc", desc);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String desc = intent.getStringExtra("desc");

        initViews(url, desc);
    }

    private void initViews(final String url, String desc) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(desc);
        setSupportActionBar(toolbar);

        dynamicAddView(toolbar, "background", R.color.toolbar_bg);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }*/

        /*webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("http://mp.weixin.qq.com")) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) webView.getLayoutParams();
                    lp.height = ScreenUtil.getDisplayHeight(TextActivity.this);
                    webView.setLayoutParams(lp);
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });*/

        webView.loadUrl(url);
    }


}
