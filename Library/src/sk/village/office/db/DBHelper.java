package sk.village.office.db;




import sk.village.office.core.Constants;
import sk.village.office.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
	private static final String	NAME		= "office.db";
	private static final int VERSION		= 2;
	
	private static DBHelper	instance	= null;
	
	
	public class EqualitySymbols{
		public static final String LESS_THAN = " < ?";
		public static final String MORE_THAN = " > ?";
		public static final String EQUAL = " = ?";
		public static final String LIKE = " like ?";
	}
	
	public static DBHelper getInstance(Context context){
		if (instance == null)
			instance = new DBHelper(context);
		return instance;
	}
	
	public DBHelper(Context context){
		super(context, NAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
	}
	
	public boolean createTable(String sql){
		SQLiteDatabase db = this.getWritableDatabase();
		try{
			db.execSQL(sql);
			
		}
		catch (SQLiteException e){
			Log.v("db error "+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	public void clearTable(String tableName){
		SQLiteDatabase db = this.getWritableDatabase();
		String deleteSQL = "DELETE FROM " + tableName;
		db.execSQL(deleteSQL);
	}
	
	public int insertValuesIntoTable(String table, ContentValues vals){
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.beginTransaction();
		int result = (int) db.insertWithOnConflict(table, null, vals, SQLiteDatabase.CONFLICT_REPLACE);
		db.setTransactionSuccessful();
		db.endTransaction();
		db.close();
		
		return result;
	}
	
	public Cursor getResultsFromSingleTable(String fromTable, String[] whereThese, String[] areEquealThese,String[] equality){
		return getResultsFromSingleTable("*",fromTable,whereThese,areEquealThese,equality);
	}
	
	public Cursor getResultsFromSingleTable(String fromTable, String[] whereThese, String[] areEquealThese){
		return getResultsFromSingleTable("*",fromTable,whereThese,areEquealThese,null);
	}
	
	public Cursor getResultsFromSingleTable(String giveMeColumns, String fromTable, String[] whereThese, String[] areEquealThese){
		return getResultsFromSingleTable(giveMeColumns,fromTable,whereThese,areEquealThese,null);
	}
	
	public Cursor getResultsFromSingleTable(String giveMeColumns, String fromTable, String[] whereThese, String[] areEquealThese,String[] equality){
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "SELECT "+giveMeColumns+" FROM "+fromTable;
		
		if(whereThese != null){ 
			sql = sql +" WHERE ";
			for(int i=0; i<whereThese.length;i++){
				if(equality == null)
					sql = sql + whereThese[i] +EqualitySymbols.EQUAL;
				else
					sql = sql + whereThese[i] +equality[i];
				if(i+1 != whereThese.length)
					sql = sql + " AND ";
			}
		}
		Cursor c = db.rawQuery(sql,areEquealThese);
		return c;
	}
	
	public Cursor getResultsForCustomSelect(String select){
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor c = db.rawQuery(select,null);
		return c;
	}

	
	public boolean doesTableExist(int data_id){
		SQLiteDatabase db = this.getWritableDatabase();
		
		String tableName = getTableName(data_id);
		
	    Cursor cursor = db.rawQuery("SELECT * FROM sqlite_master WHERE name ='"+tableName+"' and type='table'",null);
	    
	    Log.d("TABLE "+tableName+" = "+cursor.moveToFirst());
	    
	    if (!cursor.moveToFirst())
	        return false;
	    
	    return true;
	}

	private String getTableName(int data_id){
		switch (data_id) {
		case Constants.GET_CONTENT_MAYOR:
			return Tables.Mayor.TABLE_NAME;
		case Constants.GET_CONTENT_PLACES:
			return Tables.Places.TABLE_NAME;
		case Constants.GET_CONTENT_NEWS:
			return Tables.News.TABLE_NAME;
		case Constants.GET_CONTENT_OFFICE_BOARD:
			return Tables.OfficeBoard.TABLE_NAME;
		case Constants.GET_CONTENT_ATRIUM:
			return Tables.Atrium.TABLE_NAME;
		case Constants.GET_CONTENT_PARLIAMENT:
			return Tables.Parliament.TABLE_NAME;
		case Constants.GET_CONTENT_ATRIUM_PROGRAM:
			return Tables.AtriumProgram.TABLE_NAME;
			
		default:
			return "";
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}