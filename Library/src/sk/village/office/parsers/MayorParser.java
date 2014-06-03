package sk.village.office.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sk.village.office.core.Constants;
import sk.village.office.db.DBHelper;
import sk.village.office.db.Tables;
import sk.village.office.ui.BaseActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class MayorParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	public MayorParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);
		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			database.createTable(Tables.Mayor.CREATE_TABLE);
			
			JSONObject json = new JSONObject(this.response);

			values.clear();
			values.put(Tables.Mayor.NAME, json.getString(Constants.KEYWORD_NAME));
			values.put(Tables.Mayor.TEL_NUM, json.getString(Constants.KEYWORD_TEL_NUM));
			values.put(Tables.Mayor.MAIL, json.getString(Constants.KEYWORD_MAIL));
			values.put(Tables.Mayor.DESCRIPTION, json.getString(Constants.KEYWORD_DESCRIPTION));
			
			database.insertValuesIntoTable(Tables.Mayor.TABLE_NAME, values);
			
			result = true;
			
		}catch (Exception e){
		}
		finally{
			values = null;
			System.gc();
		}
		
		return result;
	}
	
	
	
	
	
	

}
