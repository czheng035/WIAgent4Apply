package czheng.wiagent;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class JavaScriptInterface {
	Context mContext;

    /** Instantiate the interface and set the context */
    JavaScriptInterface(Context c) {
        mContext = c;
    }
    


    /** Show a toast from the web page */
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    
    public void showHTML(String html) {  
       new AlertDialog.Builder(mContext)  
            .setTitle("HTML")  
            .setMessage(html)  
            .setPositiveButton(android.R.string.ok, null)  
	        .setCancelable(false)  
	        .create();  
    }
}
