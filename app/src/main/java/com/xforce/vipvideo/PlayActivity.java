package com.xforce.vipvideo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class PlayActivity extends AppCompatActivity{

    private String url;
    private WebView webView_play;
    private Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_play);
        initWebView();

    }

    @Override
    protected void onResume() {
        //横屏显示
        if (getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initWebView(){
        intent = getIntent();
        url = intent.getStringExtra("url");
        webView_play = findViewById(R.id.webView_play);
        //获取webview的配置
        WebSettings settings = webView_play.getSettings();
        //配置支持domstorage
        settings.setDomStorageEnabled(true);//启用或禁用DOM缓存
        settings.setAppCacheEnabled(false);//关闭/启用应用缓存
        settings.setSupportZoom(true);//是否可以缩放，默认true
        //settings.setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setUseWideViewPort(true); // 关键点
        settings.setDomStorageEnabled(true);

        settings.setAllowFileAccess(true); // 允许访问文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView_play.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView_play.setWebChromeClient(new WebChromeClient());
        webView_play.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
                //判断是否是广告相关的资源链接
                if (!AdFilterTool.isAd(webView.getContext(), url)) {
                    //这里是不做处理的数据
                    return super.shouldInterceptRequest(webView, url);
                } else {
                    //有广告的请求数据，我们直接返回空数据，注：不能直接返回null
                    return new WebResourceResponse(null, null, null);
                }
            }
        });

        webView_play.loadUrl(url);
    }




}
