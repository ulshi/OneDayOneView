package com.cupster.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.KeyEvent.KEYCODE_BACK;

public class H5Activity extends AppCompatActivity {

    private static final String TAG = "H5Activity";
    private static final String APP_CACAHE_DIRNAME = "/didiweb";
    public static final String KEY_H5_URL = "H5_URL";

    TextView mToolbarTitle;
    LinearLayout mContentLayout;
    WebView mWebView;
    String mUrlStr;
    boolean isCanScale  = true;

    public static void open(Context context , String url){
        Intent intent = new Intent(context , H5Activity.class);
        intent.putExtra(KEY_H5_URL ,url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.WHITE);
        } else {

        }
        setAndroidNativeLightStatusBar(this, true);

        setContentView(R.layout.activity_h5);
        initToolbar();
        initView();
        initData();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onPause() {
        dismissLoading();
        mWebView.getSettings().setJavaScriptEnabled(false);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        destoryWebView();
        dismissLoading();
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        ImageView back = findViewById(R.id.toolbar_back_left);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView scale = findViewById(R.id.toolbar_icon_msg);
        scale.setImageResource(R.mipmap.icon_web_scale);
        scale.setVisibility(View.VISIBLE);
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCanScale = !isCanScale;
                if (isCanScale){
                    ((ImageView)v).setImageResource(R.mipmap.icon_web_scalable);
                    mWebView.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
                    mWebView.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩
                }else {
                    ((ImageView)v).setImageResource(R.mipmap.icon_web_scale);
                    mWebView.getSettings().setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
                    mWebView.getSettings().setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩
                }
                loadH5();
            }
        });
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarTitle.setTextColor(getResources().getColor(R.color.text_black));
        mToolbarTitle.setTextSize(16.0f);
        mToolbarTitle.setText("详情");
    }

    private void initView() {
        mContentLayout = findViewById(R.id.h5_content_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(this.getApplicationContext());
        mWebView.setLayoutParams(params);
        mContentLayout.addView(mWebView);
        buildWebView();
    }

    private void initData() {
        if (getIntent() == null){
            finish();
            return;
        }
        mUrlStr = getIntent().getStringExtra(KEY_H5_URL);
        if ( TextUtils.isEmpty(mUrlStr) || "null".equals(mUrlStr) ){
            finish();
            return;
        }
        loadH5();
    }

    private void loadH5(){
        if (TextUtils.isEmpty(mUrlStr)){
            return;
        }
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        mWebView.clearHistory();
        mWebView.clearCache(true);
        mWebView.loadUrl(mUrlStr);
    }

    private void buildWebView() {
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    if (title.length()<6){
                        mToolbarTitle.setText(title);
                    }else if (title.length()>10){
                        mToolbarTitle.setTextSize(12f);
                        title = title.substring(0,8);
                        title = title + "...";
                        mToolbarTitle.setText(title);
                    }else {
                        mToolbarTitle.setTextSize(14f);
                        mToolbarTitle.setText(title);

                    }
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            mWebView.setWebViewClient(new WebViewClient() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    view.loadUrl(String.valueOf(request.getUrl()), request.getRequestHeaders());
                    return true;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    showLoading();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    dismissLoading();

                }
            });
        } else {
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    showLoading();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    dismissLoading();
                }
            });
        }

        //设置
        WebSettings webSettings = mWebView.getSettings();
        //设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是setBuiltInZoomControls(true)的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false=不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setAllowFileAccess(true);//可以访问文件
        webSettings.setDomStorageEnabled(true);// 开启DOM storage
        webSettings.setDatabaseEnabled(true);//开启database storage
        webSettings.setAppCacheEnabled(true);//开启Application Caches

        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
        webSettings.setAppCachePath(cacheDirPath);//设置Caches缓存目录
        //每个 Application 只调用一次 WebSettings.setAppCachePath()，WebSettings.setAppCacheMaxSize()
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    private void showLoading(){

    }
    //
    private void destoryWebView() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            mWebView.clearCache(true);
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }

    private void dismissLoading() {

    }


    protected static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

}
