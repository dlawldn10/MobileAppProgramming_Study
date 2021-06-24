package com.project.myprofileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView web;
    Button backBttn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        web = (WebView) findViewById(R.id.webView);
        backBttn = findViewById(R.id.backButton2);

        web.setWebViewClient(new WebViewClient());
        WebSettings webSet = web.getSettings();
        webSet.setBuiltInZoomControls(true);
        webSet.setJavaScriptEnabled(true);

        if(getIntent().getStringExtra("url") != null){
            web.loadUrl(getIntent().getStringExtra("url").toString());
        }else{
            Toast.makeText(getApplicationContext(), "웹페이지가 없습니다.", Toast.LENGTH_SHORT).show();
        }

        //돌아가기 버튼
        backBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }


}