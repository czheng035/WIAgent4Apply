package czheng.wiagent.jsinterface;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import czheng.wiagent.common.HTMLIntentElement;
import czheng.wiagent.db.WebIntentRepo;
import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

public class Registration {
	
	private final static String TAG_INTENT = "intent";
	
	private Context mContext;
	
	public Registration(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	private void registerIntentElement(HTMLIntentElement e) {
		WebIntentRepo repo = new WebIntentRepo(mContext);
		repo.open();
		repo.createEntry(e);
		repo.close();		
	}
	
	public void register(String resource, String url) {
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode root = cleaner.clean(resource);
		TagNode[] nodes = root.getElementsByName(TAG_INTENT, true);
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].getAttributeByName(HTMLIntentElement.ATT_ACTION) == null
					|| nodes[i].getAttributeByName(HTMLIntentElement.ATT_TYPE) == null) {
				continue;
			}
			String action = nodes[i].getAttributeByName(HTMLIntentElement.ATT_ACTION);
			String type = nodes[i].getAttributeByName(HTMLIntentElement.ATT_TYPE);
			String href = nodes[i].getAttributeByName(HTMLIntentElement.ATT_HERF) != null ? nodes[i].getAttributeByName(HTMLIntentElement.ATT_HERF) : url;
			String title = nodes[i].getAttributeByName(HTMLIntentElement.ATT_TITLE) != null ? nodes[i].getAttributeByName(HTMLIntentElement.ATT_TITLE) : "undefined";
			String disposition = nodes[i].getAttributeByName(HTMLIntentElement.ATT_DISPOSITION) != null ? nodes[i].getAttributeByName(HTMLIntentElement.ATT_DISPOSITION) : "undefined";
			HTMLIntentElement element = new HTMLIntentElement(
					action, 
					type, 
					href, 
					title, 
					disposition);
			registerIntentElement(element);
		}		
	}
	
//	public boolean register(String action, String ) {
//		
//	}
	
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
