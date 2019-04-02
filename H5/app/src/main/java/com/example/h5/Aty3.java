package com.example.h5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Aty3 extends AppCompatActivity {

    private Button btnStartAty1;
    private Button btnStartAty2;
    private Button btnStartAty3;
    private Button btnStartAty4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty3);
        btnStartAty1 = (Button) findViewById(R.id.btnStartAty1);
        btnStartAty1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Aty3.this, Aty1.class);
                startActivity(i);

            }
        });
        btnStartAty2 = (Button) findViewById(R.id.btnStartAty2);
        btnStartAty2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Aty3.this, Ayt2.class);
                startActivity(i);

            }
        });
        btnStartAty3 = (Button) findViewById(R.id.btnStartAty3);
        btnStartAty3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Aty3.this, Aty3.class);
                startActivity(i);

            }
        });
        btnStartAty4 = (Button) findViewById(R.id.btnStartAty4);
        btnStartAty4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Aty3.this, Act4.class);
                startActivity(i);

            }
        });

        //WebView
        WebView browser=(WebView)findViewById(R.id.webView);
        browser.loadUrl("http://www.classicpoetry.net/BookList.aspx");

        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);             //调用js

        browser.setWebChromeClient(new WebChromeClient());

        //设置可自由缩放网页
        //browser.getSettings().setSupportZoom(true);
        //browser.getSettings().setBuiltInZoomControls(true);

        // 如果页面中链接，如果希望点击链接继续在当前browser中响应，
        // 而不是新开Android的系统browser中响应该链接，必须覆盖webview的WebViewClient对象
        browser.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
    }

    //go back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView browser = (WebView) findViewById(R.id.webView);
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && browser.canGoBack()) {
            browser.goBack();
            return true;
        }
        //  return true;
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}
