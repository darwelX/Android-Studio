package com.ejemplo.practica.ejemplowebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView) findViewById(R.id.webView);

        String url = "file:///android_asset/index.html";
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);

        //le decimos al WebView que no habra una nueva ventana para cada enlace que se clikeado
        web.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriderUrlLoading(WebView view, String url){
                return false;
            }
        });

        web.loadUrl(url);
    }
}
