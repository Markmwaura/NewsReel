package dukaconnect.newsapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by mark on 4/25/17.
 */

public class WebViewActivity extends BaseActivity {
    String url;
    private WebView mWebview ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///setContentView(R.layout.web_view);

        ////mWebview = (WebView) findViewById(R.id.webView1);

       /// mWebview.loadUrl("https://thenextweb.com/");

        Log.i("work", getIntent().getExtras().getString("Url"));

        mWebview  = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        mWebview.loadUrl(getIntent().getExtras().getString("Url"));
        setContentView(mWebview );


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
