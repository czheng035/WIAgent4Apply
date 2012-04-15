package czheng.wiagent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WIAgentWebView extends Activity implements OnClickListener {    

    private EditText etUrl;
    private WebView mWebView;
    private InputMethodManager manager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);
        
        mWebView = (WebView) findViewById(R.id.webView);
        Button go = (Button) findViewById(R.id.bGo);
        Button backward = (Button) findViewById(R.id.bGoBackward);
        Button forward = (Button) findViewById(R.id.bGoForward);
        Button home = (Button) findViewById(R.id.bHome);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        etUrl = (EditText) findViewById(R.id.etAddrBar);
        
        mWebView.requestFocus();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);                
//                view.loadUrl("javascript:" +
//                        "var s = 'function Intent(action, type) {" +
//                        "           this.action = action;" +
//                        "           this.type = type;" +
//                        "         }" +
//                        "         function Navigator() {}" +
//                        "         Navigator.prototype.startActivity = function(intent) {" +
//                        "           WIService.startActivity(intent.action, intent.type);" +
//                        "         };" +
//                        "         window.navigator = new Navigator();';" +
//                		"var head = document.getElementsByTagName('head')[0];" +
//                		"var jsNode = document.createElement('script');" +
//                		"jsNode.setAttribute('type', 'text/javascript');" +
//                		"jsNode.innerHTML = s;" +
//                		"head.appendChild(jsNode);");
                
                view.loadUrl("javascript:" +
                        "var s = 'function Intent(action, type) {" +
                        "           this.action = action;" +
                        "           this.type = type;" +
                        "         }" +
                        "         function Navigator() {}" +
                        "         Navigator.prototype.startActivity = function(intent) {" +
                        "           WIService.startActivity(intent.action, intent.type);" +
                        "         };" +
                        "         window.navigator = new Navigator();';" +
                        "var head = document.getElementsByTagName('head')[0];" +
                        "var jsNode = document.createElement('script');" +
                        "jsNode.setAttribute('type', 'text/javascript');" +
                        "jsNode.innerHTML = s;" +
                        "head.appendChild(jsNode);" +
                        "var iframes = document.getElementsByTagName('iframe');" +
                        "for (var i = 0; i < iframes.length; i++) {" +
                        "  var fJsNode = iframes[i].contentDocument.createElement('script');" +
                        "  fJsNode.setAttribute('type', 'text/javascript');" +
                        "  fJsNode.innerHTML = s;" +
                        "  var fHead = iframes[i].contentDocument.getElementsByTagName('head')[0];" +
                        "  fHead.appendChild(fJsNode);" +
                        "}");
//                view.loadUrl("javascript:WIService.alert(document.getElementsByTagName('html')[0].innerHTML);");
//                Toast.makeText(mWebView.getContext(), "Ready to invoke Webintents", Toast.LENGTH_SHORT).show();
            }
            
        });
        
        mWebView.addJavascriptInterface(new WebIntentsService(this), "WIService");
                
        go.setOnClickListener(this);
        backward.setOnClickListener(this);
        forward.setOnClickListener(this);
        home.setOnClickListener(this);
        refresh.setOnClickListener(this);
//        mWebView.loadUrl("file:///android_asset/index.html");
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        manager.hideSoftInputFromWindow(etUrl.getWindowToken(), 0);
        mWebView.loadUrl("http://examples.webintents.org/usage/startActivity/index.html");                
//        mWebView.loadUrl("http://examples.webintents.org/intents/share/share.html");
        
        
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.bGo:
            mWebView.loadUrl(etUrl.getText().toString());            
            manager.hideSoftInputFromWindow(etUrl.getWindowToken(), 0);
            break;
            
        case R.id.bGoBackward:
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            }         
            break;

        case R.id.bGoForward:
            if (mWebView.canGoForward()) {
                mWebView.goForward();
            }       
            break;
            
        case R.id.bRefresh:
            mWebView.reload();
            break;
            
        case R.id.bHome:
            mWebView.loadUrl("http://webintents.org/");       
            break;
            
        default:
            break;
        }
    }
    
}
