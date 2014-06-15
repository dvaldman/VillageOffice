package sk.village.office.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sk.village.office.core.Constants;
import sk.village.office.db.DBHelper;
import sk.village.office.db.Tables;
import sk.village.office.util.Log;
import android.content.ContentValues;
import android.content.Context;


public class AtriumProgramParser {
	
	private String response;
	private DBHelper database;
	
	ContentValues values;
	
	public AtriumProgramParser(String response, Context context){
		this.response = response;
		this.database = DBHelper.getInstance(context);

		values = new ContentValues();	
	}

	
	public boolean parseResponse(){
		boolean result = false;
		try{
			JSONObject json = new JSONObject(this.response);
			if(!database.createTable(Tables.AtriumProgram.CREATE_TABLE))
				database.clearTable(Tables.AtriumProgram.TABLE_NAME);
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
			Log.i("adding "+i);
			values.clear();
			values.put(Tables.AtriumProgram.TITLE, json.getString(Constants.KEYWORD_TITLE));
			values.put(Tables.AtriumProgram.DATE, json.getString(Constants.KEYWORD_DATE));
			values.put(Tables.AtriumProgram.SHORT_DESC, json.getString(Constants.KEYWORD_SHORT_DESC));
			values.put(Tables.AtriumProgram.IMAGE, json.getString(Constants.KEYWORD_IMAGE_THB));
			values.put(Tables.AtriumProgram.PDF, json.getString(Constants.KEYWORD_PDF));
			database.insertValuesIntoTable(Tables.AtriumProgram.TABLE_NAME, values);
			
		}
	}
	
	
	
	

}
