package com.example.amrit.cmchguide.Map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.amrit.cmchguide.R;

public class MapActivity extends AppCompatActivity {

    private WebView mapWebViewWhole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapWebViewWhole = (WebView) findViewById(R.id.mapWebViewWhole);

        mapWebViewWhole.setInitialScale(1);
        mapWebViewWhole.getSettings().setSupportZoom(true);
        mapWebViewWhole.getSettings().setLoadWithOverviewMode(true);
        mapWebViewWhole.getSettings().setUseWideViewPort(true);
        mapWebViewWhole.getSettings().setBuiltInZoomControls(true);
        initialApp();
    }

    private void initialApp() {
        mapWebViewWhole.loadUrl("file:///android_asset/cu.html");
    }
}
