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
import sk.village.office.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class OfficeBoardParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	public OfficeBoardParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);

		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			JSONObject json = new JSONObject(this.response);
			if(!database.createTable(Tables.OfficeBoard.CREATE_TABLE))
				database.clearTable(Tables.OfficeBoard.TABLE_NAME);
			parseNews(json.getJSONArray(Constants.KEYWORD_NEWS));
			result = true;
			
		}catch (Exception e){}
		finally{
			values = null;
			System.gc();
		}
		
		return result;
	}
	
	private void parseNews(JSONArray jsonArray) throws JSONException{
		
		for (int i = 0; i< jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			
			values.clear();
			values.put(Tables.OfficeBoard.TITLE, json.getString(Constants.KEYWORD_TITLE));
			values.put(Tables.OfficeBoard.DATE, json.getString(Constants.KEYWORD_DATE));
			values.put(Tables.OfficeBoard.LONG_DESC, json.getString(Constants.KEYWORD_LONG_DESC));
			values.put(Tables.OfficeBoard.SHORT_DESC, json.getString(Constants.KEYWORD_SHORT_DESC));
			values.put(Tables.OfficeBoard.IMAGE, json.getString(Constants.KEYWORD_IMAGE_THB));
			database.insertValuesIntoTable(Tables.OfficeBoard.TABLE_NAME, values);
			
		}
	}
	
	
	
	

}
