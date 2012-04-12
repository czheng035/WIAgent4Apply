package czheng.wiagent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WIAgentWebView extends Activity implements OnClickListener {    

    private EditText url;
    private WebView mWebView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        
        mWebView = (WebView) findViewById(R.id.webView);
        Button go = (Button) findViewById(R.id.bGo);
        url = (EditText) findViewById(R.id.etAddrBar);
        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        
        mWebView.setWebViewClient(new WebViewClient() {
            
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                v.loadUrl(url);
//                mWebView.loadUrl("javascript:" +
//                        "function Intent(action, type) {" +
//                        "   this.action = action;" +
//                        "   this.type = type;" +
//                        "};" +
//                        "function Navigator() {};" +
//                        "Navigator.prototype.startActivity = function(intent) {" +
//                        "   WIService.startActivity(intent.action, intent.type)" +
//                        "};" +
//                        "window.navigator = new Navigator();");
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
//                mWebView.loadUrl("javascript:" +
//                        "function Intent(action, type) {" +
//                        "   this.action = action;" +
//                        "   this.type = type;" +
//                        "};" +
//                        "function Navigator() {};" +
//                        "Navigator.prototype.startActivity = function(intent) {" +
//                        "   WIService.startActivity(intent.action, intent.type)" +
//                        "};" +
//                        "window.navigator = new Navigator();");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                mWebView.loadUrl("javascript:" +
                        "function Intent(action, type) {" +
                        "   this.action = action;" +
                        "   this.type = type;" +
                        "};" +
                        "function Navigator() {};" +
                        "Navigator.prototype.startActivity = function(intent) {" +
                        "   WIService.startActivity(intent.action, intent.type)" +
                        "};" +
                        "window.navigator = new Navigator();");
                Toast.makeText(WIAgentWebView.this, "Ready", Toast.LENGTH_SHORT).show();
            }
            
        });
        
        mWebView.addJavascriptInterface(new WebIntentsService(this), "WIService");
                
        go.setOnClickListener(this);
        
//        mWebView.loadUrl("javascript:" +
//                "function Intent(action, type) {" +
//                "   this.action = action;" +
//                "   this.type = type;" +
//                "};" +
//                "function Navigator() {};" +
//                "Navigator.prototype.startActivity = function(intent) {" +
//                "   WIService.startActivity(intent.action, intent.type)" +
//                "};" +
//                "window.navigator = new Navigator();");
        
        mWebView.loadUrl("http://examples.webintents.org/usage/startActivity/index.html");                
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.bGo:
            mWebView.loadUrl(url.getText().toString());
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(url.getWindowToken(), 0);
            break;

        default:
            break;
        }
    }
    
}
