package czheng.wiagent;

import czheng.wiagent.jsinterface.Registration;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText url;
	private WebView mWebView;
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWebView = (WebView) findViewById(R.id.webView);
        Button go = (Button) findViewById(R.id.bGo);
        url = (EditText) findViewById(R.id.etAddrBar);
        go.setOnClickListener(this);
        
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setWebViewClient(new WebViewClient() {
        	
        	@Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                v.loadUrl(url);
                return true;
            }

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
//				mWebView.loadUrl("javascript:" +
//						"function Intent(action, type) {" +
//						"	this.action = action;" +
//						"	this.type = type;" +
//						"};" +
//						"function Navigator() {};" +
//						"Navigator.prototype.startActivity = function(intent) {" +
//						"	AndroidNavigator.startActivity(intent.action, intent.type)" +
//						"};" +
//						"window.navigator = new Navigator();");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
//				mWebView.loadUrl("registration.showToast('hello');registration.showToast('hello2')");
//				mWebView.loadUrl("file:///android_asset/js/test.js");
//				String js = "javascript:" +
//						"if (document.getElementsByTagName('intent').length !== 0) {" +
////						"	registration.showToast(document.getElementsByTagName('intent').length)" +
//						" 	registration.showToast('hello')" +
//						"}";
////						"registration.showToast(document.getElementsByTagName('intent'))";
//				mWebView.loadUrl(js);
//				mWebView.loadUrl("javascript:window.Intent=navigator");
//				mWebView.loadUrl("javascript:" +
//						"var Intent = function(action, type) {" +
//						"	this.action = action;" +
//						"	this.type = type;" +
//						"}" +
//						"var navigator.startActivity = function(Intent intent) {" +
//						"	AndroidNavigator.startActivity(intent.action, intent.type)" +
//						"}");
//				mWebView.loadUrl("javascript:window.navigator.startActivity=AndroidNavigator.startActivity");
//				mWebView.loadUrl("javascript:registration.showHTML('<html>' + document.getElementsByTagName('html')[0].innerHTML + '</html>');");
				
			}    
			
        });
        mWebView.setWebChromeClient(new MyWebChromeClient());
//        mWebView.loadUrl("http://examples.webintents.org/intents/share/share.html");
//        mWebView.loadUrl("javascript:alert(navigator.appName)");
        mWebView.addJavascriptInterface(new Registration(this), "registration");
//        mWebView.addJavascriptInterface(new Intent(), "window.Intent");
//        mWebView.addJavascriptInterface(new Navigator(), "AndroidNavigator");
//        String url = "file:///android_asset/index.html";
//        String js = "javascript:" +
//        		"xmlhttp = new XMLHttpRequest();" +
//        		"xmlhttp.open('GET', '" + url + "', false);" +
//        		"xmlhttp.send()";
//        mWebView.loadUrl("file:///android_asset/index.html");
//        mWebView.loadUrl("file:///android_asset/Mememator.html");
        mWebView.loadUrl("http://examples.webintents.org/intents/share/share.html");
//        mWebView.loadData("<html><body>Hi dude<br><br>Hi again</body></html>", "text/html", "UTF-8");
//        mWebView.loadUrl("http://www.google.com");
//        mWebView.loadUrl("http://examples.webintents.org/usage/startActivity/index.html");
    }
    
//    private class HelloWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
////        public void onPageFinished(WebView view, String url)  
////        {  
////            /* This call inject JavaScript into the page which just finished loading. */  
////            view.loadUrl("javascript:window.HTMLOUT.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
////        }
//
//    }
    
    private class MyWebChromeClient extends WebChromeClient {
    	@Override
		public boolean onJsAlert(WebView view, String url, String message,final JsResult result) {
		//handle Alert event, here we are showing AlertDialog
			new AlertDialog.Builder(MainActivity.this)
			   .setTitle("JavaScript Alert !")
			   .setMessage(message)
			   .setPositiveButton(android.R.string.ok,
			       new AlertDialog.OnClickListener() {
			          public void onClick(DialogInterface dialog, int which) {
			                 // do your stuff
			                     result.confirm();
			               }
			           }).setCancelable(false).create().show();
			return true;
		}
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
	
	public static class Navigator {
		public void startActivity(String action, String type) {
			System.out.println("Test navigator");
		}		
	}
	
//	public static class Intents {
//		public Intents() {}
//	}
}