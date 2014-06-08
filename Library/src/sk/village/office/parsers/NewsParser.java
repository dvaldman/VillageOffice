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


public class NewsParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	public NewsParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);

		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			JSONObject json = new JSONObject(this.response);

			if(!database.createTable(Tables.News.CREATE_TABLE))
				database.clearTable(Tables.News.TABLE_NAME);
			
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
			values.put(Tables.News.TITLE, json.getString(Constants.KEYWORD_TITLE));
			values.put(Tables.News.DATE, json.getString(Constants.KEYWORD_DATE));
			values.put(Tables.News.LONG_DESC, json.getString(Constants.KEYWORD_LONG_DESC));
			values.put(Tables.News.SHORT_DESC, json.getString(Constants.KEYWORD_SHORT_DESC));
			values.put(Tables.News.IMAGE, json.getString(Constants.KEYWORD_IMAGE_THB));
			Log.i("image "+json.getString(Constants.KEYWORD_IMAGE_THB));
			database.insertValuesIntoTable(Tables.News.TABLE_NAME, values);
			
		}
	}
	
	
	
	

}
