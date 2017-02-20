package com.dnkilic.surveydonkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wvSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://goo.gl/forms/CsmpZBW2DZWshfYh1";

        wvSurvey = (WebView) findViewById(R.id.wvSurvey);
        wvSurvey.setWebViewClient(new WebViewClient());
        WebSettings webSettings = wvSurvey.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvSurvey.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wvSurvey.canGoBack())
                    {
                        wvSurvey.goBack();
                    }
                    else
                    {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
