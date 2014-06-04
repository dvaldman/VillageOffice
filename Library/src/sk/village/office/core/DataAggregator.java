package sk.village.office.core;



import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import sk.village.office.communication.AssetsClient;
import sk.village.office.communication.URLBuilder;
import sk.village.office.communication.WebClient;
import sk.village.office.db.DBHelper;
import sk.village.office.parsers.AtriumParser;
import sk.village.office.parsers.MayorParser;
import sk.village.office.parsers.NewsParser;
import sk.village.office.parsers.OfficeBoardParser;
import sk.village.office.parsers.ParliamentParser;
import sk.village.office.parsers.PlacesParser;
import sk.village.office.util.ConnectionChecker;
import sk.village.office.util.Log;
import android.content.Context;

public class DataAggregator {
	
	private static DataAggregator instance;
	private static Context context;
	private static Configuration config;
	public static DBHelper database;
	
	
	private DataAggregator(Context cont){
		context = cont;
		config = Configuration.getInstance(cont);
		database = DBHelper.getInstance(cont);
	}
	
	
	public static DataAggregator getInstance(Context cont){
		if(instance == null)
			instance = new DataAggregator(cont);
		return instance;
	}
	
	public boolean isOnline(){
		return ConnectionChecker.isOnline(context);
	}

	
	public boolean updateContetnt(int data_id){
		boolean result = false;
		
		try {
			
			if(doesDataNeedToBeUpdated(data_id)){
				String response = getData(data_id);
				Log.i("response = "+response);
				parseString(response, data_id);
			}
			result = true;
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private String getData(int data_id) throws ClientProtocolException, IOException{
		if(!config.isStaticData(data_id) && isOnline())
			return getDataOnline(data_id);
		else
			return getDataOffline(data_id);
	}
	
	private String getDataOnline(int data_id) throws ClientProtocolException, IOException{
		return new WebClient(new URLBuilder(context).getOnlineURL(data_id)).processRequest();
	}
	
	private String getDataOffline(int data_id){
		return new AssetsClient(context).readStringFromFile(new URLBuilder(context).getOfflineURL(data_id));
	}
	
	private boolean parseString(String response, int data_id){
		switch (data_id) {
		case Constants.GET_CONTENT_MAYOR:
			return new MayorParser(response, context).parseResponse();
		case Constants.GET_CONTENT_PLACES:
			return new PlacesParser(response, context).parseResponse();
		case Constants.GET_CONTENT_NEWS:
			return new NewsParser(response, context).parseResponse();
		case Constants.GET_CONTENT_OFFICE_BOARD:
			return new OfficeBoardParser(response, context).parseResponse();
		case Constants.GET_CONTENT_ATRIUM:
			return new AtriumParser(response, context).parseResponse();
		case Constants.GET_CONTENT_PARLIAMENT:
			return new ParliamentParser(response, context).parseResponse();
			
		default:
			return false;
		}
	}
	
	public boolean doesDataNeedToBeUpdated(int data_id){
		if(Configuration.getInstance(context).isStaticData(data_id)){
			if(database.doesTableExist(data_id))
				return false;
			else
				return true;
		}
		return true;
	}
	
}
