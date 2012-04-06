package czheng.wiagent.db;

import java.io.File;

import czheng.wiagent.common.HTMLIntentElement;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class WebIntentRepo {

	public static final String KEY_RID = "_id";
	public static final String KEY_ACTION = "wi_action";
	public static final String KEY_TYPE = "wi_type";
	public static final String KEY_HREF = "wi_href";
	public static final String KEY_TITLE = "wi_title";
	public static final String KEY_DISPOSIT = "wi_disposition";
	
	private static final String DB_FILE = "wi_repo.sqlite";
	private static final String DB_TABLE_HTMLINTENT = "html_intent";
	
	private Context mContext;
	private SQLiteDatabase db;
	
	public WebIntentRepo(Context mContext) {
		super();
		this.mContext = mContext;
	}	
	
//	public void open() {		
//		File file = new File(mContext.getExternalFilesDir(null), DB_NAME);
//		if (file.exists() && file.isDirectory()) {
//			file.delete();
//		}
//		db = SQLiteDatabase.openOrCreateDatabase(file, null);		
//	}
	
//	public void close() {
//		db.close();
//	}
	
	private SQLiteOpenHelper dbHelper;
//	
//	private SQLiteOpenHelper dbHelper = new SQLiteOpenHelper(mContext, DB_NAME, null, 1) {
//		
//		@Override
//		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public void onCreate(SQLiteDatabase db) {
//			// TODO Auto-generated method stub			
//			String sql = "CREATE TABLE IF NOT EXIST " + 
//					DB_NAME + "." + DB_TABLE_HTMLINTENT + " (" +
//					KEY_RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//					KEY_ACTION + " TEXT NOT NULL, " +
//					KEY_TYPE + " TEXT NOT NULL, " +
//					KEY_HREF + " TEXT NOT NULL, " +
//					KEY_TITLE + " TEXT NOT NULL, " +
//					KEY_DISPOSIT + " TEXT NOT NULL);";
//			db.execSQL(sql);
//		}
//	};
		
//	public WebIntentRepo(Context mContext) {		
//		this.mContext = mContext;
//	}
//	
	public void open() {
		File file = new File(mContext.getExternalFilesDir(null), DB_FILE);
		if (file.exists() && file.isDirectory()) {
			file.delete();
		}
		dbHelper = new WISQLiteOpenHelper(mContext, file.getAbsolutePath());
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long createEntry(HTMLIntentElement e) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_ACTION, e.getAction());
		cv.put(KEY_TYPE, e.getType());
		cv.put(KEY_HREF, e.getHref());
		cv.put(KEY_DISPOSIT, e.getDisposition());
		cv.put(KEY_TITLE, e.getTitle());
		return db.insert(DB_TABLE_HTMLINTENT, null, cv);
	}
	
	private class WISQLiteOpenHelper extends SQLiteOpenHelper {
		
		public WISQLiteOpenHelper(Context mContext, String name) {
			super(mContext, name, null, 1);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub			
			String sql = "CREATE TABLE IF NOT EXISTS " + 
					DB_TABLE_HTMLINTENT + " (" +
					KEY_RID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_ACTION + " TEXT NOT NULL, " +
					KEY_TYPE + " TEXT NOT NULL, " +
					KEY_HREF + " TEXT NOT NULL, " +
					KEY_TITLE + " TEXT NOT NULL, " +
					KEY_DISPOSIT + " TEXT NOT NULL);";
			db.execSQL(sql);
		}
	}
}
