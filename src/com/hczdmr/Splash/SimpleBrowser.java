package com.hczdmr.Splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HaciOzdemir on 23/04/15.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {
    WebView ourBrowser;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrowser = (WebView) findViewById(R.id.wvBrowser);
        //youtube gibi siteler icin javascript enable ettik
        ourBrowser.getSettings().setJavaScriptEnabled(true);
        // zoom out yaparak icerigi ekran GENISLIGINE sigdirir
        ourBrowser.getSettings().setLoadWithOverviewMode(true);
        // HTML viewport u destekler.
        // viewport nedir? sayfanin genislik ve yukseklik degerlerini bildirerek mobil icin uyumlu hale getrmye yarar.
        ourBrowser.getSettings().setUseWideViewPort(true);
        //zoom yapabilmek icin
        ourBrowser.getSettings().setBuiltInZoomControls(true);
        //telefonun tarayicisina gidip orda acilmasin diye
        ourBrowser.setWebViewClient(new WebViewClient());
        ourBrowser.loadUrl("http://www.haciozdemir.wordpress.com");

        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button forward = (Button) findViewById(R.id.bForward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bGo:
                String myWebSite = url.getText().toString();
                ourBrowser.loadUrl(myWebSite);
                //edittext i kullandiktan sonra keyboardi sakla
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(url.getWindowToken(),0);
                break;
            case R.id.bBack:
                if(ourBrowser.canGoBack())
                    ourBrowser.goBack();
                break;
            case R.id.bRefresh:
                ourBrowser.reload();
                break;
            case R.id.bForward:
                if(ourBrowser.canGoForward())
                    ourBrowser.goForward();
                break;
            case R.id.bHistory:
                ourBrowser.clearHistory();
                break;
        }
    }
}
