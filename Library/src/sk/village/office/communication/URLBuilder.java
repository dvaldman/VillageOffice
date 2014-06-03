package sk.village.office.communication;

import sk.village.office.core.Configuration;
import sk.village.office.core.Constants;
import sk.village.office.util.ConnectionChecker;
import android.content.Context;

public class URLBuilder {

	Context context;
	Configuration config;
	
	public URLBuilder(Context cont){
		this.context = cont;
		config = Configuration.getInstance(context);
	}
	
	public String getURL(int data_id){
		String url = "";
		if(!config.isStaticData(data_id) && ConnectionChecker.isOnline(context))
			url = Constants.URL_BASE_ONLINE;
		else
			url = Constants.URL_BASE_OFFLINE;
		
		return url.replace(Constants.PLACEHOLDER_STRING,getFeedName(data_id));
	}
	
	public String getOnlineURL(int data_id){
		return Constants.URL_BASE_ONLINE.replace(Constants.PLACEHOLDER_STRING,getFeedName(data_id));
	}
	
	public String getOfflineURL(int data_id){
		return Constants.URL_BASE_OFFLINE.replace(Constants.PLACEHOLDER_STRING,getFeedName(data_id));
	}
	
	private String getFeedName(int data_id){
		switch (data_id) {
		case Constants.GET_CONTENT_MAYOR:
			return Constants.URL_MAYOR;
		case Constants.GET_CONTENT_NEWS:
			return Constants.URL_NEWS;
		case Constants.GET_CONTENT_PLACES:
			return Constants.URL_PLACES;
		case Constants.GET_CONTENT_OFFICE_BOARD:
			return Constants.URL_OFFICE_BOARD;
		case Constants.GET_CONTENT_ATRIUM:
			return Constants.URL_ATRIUM;
		case Constants.GET_CONTENT_PARLIAMENT:
			return Constants.URL_PARLIAMENT;
		default:
			return "";
		}
	}
}
