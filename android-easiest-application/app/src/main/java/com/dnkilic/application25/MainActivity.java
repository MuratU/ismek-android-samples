package com.dnkilic.application25;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = (WebView) findViewById(R.id.webView);
        page.getSettings().setJavaScriptEnabled(true);
        page.setWebViewClient(new WebViewClient());
        page.loadUrl("https://www.google.com.tr");
    }

    @Override
    public void onBackPressed() {
        if (page.canGoBack())
        {
            page.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
