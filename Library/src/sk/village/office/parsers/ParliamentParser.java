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


public class ParliamentParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	private List<String> categories;
	
	public ParliamentParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);

		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			JSONObject json = new JSONObject(this.response);
			if(!database.createTable(Tables.Parliament.CREATE_TABLE))
				database.clearTable(Tables.Parliament.TABLE_NAME);
			parseNews(json.getJSONArray(Constants.KEYWORD_PARLIAMENT));
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
			values.put(Tables.Parliament.NAME, json.getString(Constants.KEYWORD_NAME));
			values.put(Tables.Parliament.AGE, json.getString(Constants.KEYWORD_AGE));
			values.put(Tables.Parliament.JOB, json.getString(Constants.KEYWORD_JOB));
			values.put(Tables.Parliament.VOTE_LOCATION, json.getString(Constants.KEYWORD_VOTE_LOCATION));
			values.put(Tables.Parliament.IMAGE, json.getString(Constants.KEYWORD_IMAGE_THB));
			values.put(Tables.Parliament.TIME_INTERVAL, json.getString(Constants.KEYWORD_TIME_INTERVAL));
			values.put(Tables.Parliament.EMAIL, json.getString(Constants.KEYWORD_EMAIL));
			values.put(Tables.Parliament.COALITION, json.getString(Constants.KEYWORD_COALITION));
			values.put(Tables.Parliament.FUNCTION, json.getString(Constants.KEYWORD_FUNCTION));
			values.put(Tables.Parliament.PARTY, json.getString(Constants.KEYWORD_POLIT_PARTY));
			database.insertValuesIntoTable(Tables.Parliament.TABLE_NAME, values);
			
		}
	}
	

}
