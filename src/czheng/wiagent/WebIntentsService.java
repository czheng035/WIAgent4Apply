package czheng.wiagent;

import android.content.Context;

public class WebIntentsService {
    
    private Context mContext;    
    
    public WebIntentsService(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void startActivity(String action, String type) {
        Intents intents = new Intents(mContext);
        intents.startActivity(new Intent(action, type));        
    }
    
}
