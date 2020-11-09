package com.ai.covidainuas.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ai.covidainuas.fragment.IdnFragment;
import com.ai.covidainuluas.R;

public class Map extends AppCompatActivity {
    WebView wvMap;
    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        bar = (ProgressBar) findViewById(R.id.pbWvdetail);
        wvMap = findViewById(R.id.wvMapDetail);
        WebSettings webSettings = wvMap.getSettings();
        wvMap.setWebViewClient(new Map.myWebclient());
        webSettings.setJavaScriptEnabled(true);

        wvMap.loadUrl("https://experience.arcgis.com/experience/bf4eb5d76e98423c865678e32c8937d4");


    }
    public class myWebclient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }


        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }



}