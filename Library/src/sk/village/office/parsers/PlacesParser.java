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


public class PlacesParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	private List<String> categories;
	
	public PlacesParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);

		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			JSONObject json = new JSONObject(this.response);
			
			if(!database.createTable(Tables.Places.CREATE_TABLE))
				database.clearTable(Tables.Places.TABLE_NAME);
			if(!database.createTable(Tables.Categories.CREATE_TABLE))
				database.clearTable(Tables.Categories.TABLE_NAME);
			
			parsePlaces(json.getJSONArray(Constants.KEYWORD_PLACES));
			insertCategories();
			result = true;
		}catch (Exception e){
		}
		finally{
			values = null;
			System.gc();
		}
		
		return result;
	}
	
	private void parsePlaces(JSONArray jsonArray) throws JSONException{
		
		for (int i = 0; i< jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			String category = json.getString(Constants.KEYWORD_CATEGORY);
			values.clear();
			values.put(Tables.Places.NAME, json.getString(Constants.KEYWORD_NAME));
			values.put(Tables.Places.LONGITUDE, json.getString(Constants.KEYWORD_LONGITUDE));
			values.put(Tables.Places.LATITUDE, json.getString(Constants.KEYWORD_LATITUDE));
			values.put(Tables.Places.CATEGORY, category);
			database.insertValuesIntoTable(Tables.Places.TABLE_NAME, values);
			addCategory(category);
		}

	}
	
	
	private void addCategory(String cat){
		if(categories == null)
			categories = new ArrayList<String>();
		if(!categories.contains(cat))
			categories.add(cat);
	}
	
	private void insertCategories(){
		for(String cat:categories){
			values.clear();
			values.put(Tables.Categories.NAME, cat);
			database.insertValuesIntoTable(Tables.Categories.TABLE_NAME, values);
		}
	}
	

}
