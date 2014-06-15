package sk.village.office.core;


import java.util.ArrayList;
import java.util.List;

import sk.village.office.R;
import android.content.Context;

public class Configuration {
	
	private static Configuration instance;
	private static Context context;
	
	
	public static String placesCategory1 = "";
	public static String placesCategory2 = "";
	public static String placesCategory3 = "";
	public static String placesCategory4 = "";
	
	
	public static boolean reportToMail = true;
	public static String mailForReport = "";
	public static int numberOfReportPhotos = 1;
	
	
	private static List<Boolean> staticData;
	
	private Configuration(Context cont){
		context = cont;
		initConfiguration();
	}
	
	public static Configuration getInstance(Context cont){
		if(instance == null)
			instance = new Configuration(cont);
		return instance;
	}
	
	private void initConfiguration(){
//		data
		staticData = new ArrayList<Boolean>();
		staticData.add((context.getResources().getBoolean(R.bool.static_mayor)));
		staticData.add((context.getResources().getBoolean(R.bool.static_places)));
		staticData.add((context.getResources().getBoolean(R.bool.static_news)));
		staticData.add((context.getResources().getBoolean(R.bool.static_contacts)));
		staticData.add((context.getResources().getBoolean(R.bool.static_OfficeBoard)));
		staticData.add((context.getResources().getBoolean(R.bool.static_atrium)));
		staticData.add((context.getResources().getBoolean(R.bool.static_parliament)));
		staticData.add((context.getResources().getBoolean(R.bool.static_atrium_program)));
		
//		places categories
		placesCategory1 = context.getResources().getString(R.string.places_category1);
		placesCategory2 = context.getResources().getString(R.string.places_category2);
		placesCategory3 = context.getResources().getString(R.string.places_category3);
		placesCategory4 = context.getResources().getString(R.string.places_category4);
		
		
//		report
		reportToMail = context.getResources().getBoolean(R.bool.report_to_mail);
		mailForReport = context.getResources().getString(R.string.mail_for_reporting);
		numberOfReportPhotos = context.getResources().getInteger(R.integer.number_of_report_photos);
		
	}

	public boolean isStaticData(int type){
		return staticData.get(type);
	}
	
}
