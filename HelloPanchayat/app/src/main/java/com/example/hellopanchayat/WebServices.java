package com.example.hellopanchayat;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_services);

       Bundle bundle;
       bundle=getIntent().getExtras();
       String uri = bundle.getString("url");

        WebView w = (WebView) findViewById(R.id.web);
        w.loadUrl(uri);
        w.getSettings().setJavaScriptEnabled(true);
        w.setWebViewClient(new WebViewClient());

        //w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setLoadWithOverviewMode(true);
        w.getSettings().setUseWideViewPort(true);

        w.getSettings().setSupportZoom(true);
        w.getSettings().setBuiltInZoomControls(true);
        w.getSettings().setDisplayZoomControls(false);

        w.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        w.setScrollbarFadingEnabled(false);
    }
}