package com.xforce.vipvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import static android.view.KeyEvent.KEYCODE_BACK;


public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    private String url;
    private WebView webView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        intent = getIntent();
        url = intent.getStringExtra("url");
        webView = findViewById(R.id.webView);
        initWebView();
        webView.loadUrl(url);
        ImageView imageView_play = (ImageView) findViewById(R.id.imageview_play);
        imageView_play.setOnClickListener(this);

    }

    private void initWebView(){


        //获取webview的配置
       WebSettings settings = webView.getSettings();
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
        webView.canGoBack();
        //同时加载Https和Http混合模式
        if (Build.VERSION.SDK_INT >= 21) { settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW ); }


        webView.setWebViewClient(new WebViewClient(){
            //@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    view.loadUrl(url);
                    return true;

                }else {
                    return true;
                }
            }
        });

        //webView.setWebChromeClient(new WebChromeClient());

        //webView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_play:
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                intent.putExtra("url", "http://app.baiduoos.cn:2019/vip/?url=" + webView.getUrl());
                startActivity(intent);
                break;
        }

    }

    //按返回键回退网页
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
