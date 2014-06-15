package sk.village.office.core;

public class Constants {
	
	/****************************** place holders ******************************/
	public static final String PLACEHOLDER_STRING = "{%s}";
	
	/****************************** Handler keys ******************************/
	public static final int MESSAGE_SELECT_MAIN		= 0;
	public static final int MESSAGE_SELECT_MAP 		= 1;
	public static final int MESSAGE_SELECT_OFFICE 	= 2;
	public static final int MESSAGE_SELECT_CONTACTS = 3;
	public static final int MESSAGE_SELECT_MORE 	= 4;
	public static final int MESSAGE_DESELECT		= 5;
	public static final int MESSAGE_GET_ADDRESS		= 6;
	
	/****************************** Json keywords ******************************/
	public static final String	KEYWORD_ID 				= "id";
	public static final String	KEYWORD_NAME 			= "name";
	public static final String	KEYWORD_TEL_NUM			= "telNum";
	public static final String	KEYWORD_MAIL			= "mail";
	public static final String	KEYWORD_DESCRIPTION		= "description";
	public static final String	KEYWORD_ADDRESS			= "address";
	public static final String	KEYWORD_PLACES			= "places";
	public static final String	KEYWORD_LATITUDE		= "latitude";
	public static final String	KEYWORD_LONGITUDE		= "longitude";
	public static final String	KEYWORD_CATEGORY		= "category";
	public static final String	KEYWORD_NEWS			= "news";
	public static final String	KEYWORD_TITLE			= "title";
	public static final String	KEYWORD_DATE			= "date";
	public static final String	KEYWORD_SHORT_DESC		= "shortDesc";
	public static final String	KEYWORD_LONG_DESC		= "longDesc";
	public static final String	KEYWORD_IMAGE_THB		= "image_thb";
	public static final String	KEYWORD_AGE				= "age";
	public static final String	KEYWORD_JOB				= "job";
	public static final String	KEYWORD_VOTE_LOCATION	= "voteLocation";
	public static final String	KEYWORD_TIME_INTERVAL	= "timeInterval";
	public static final String	KEYWORD_EMAIL			= "email";
	public static final String	KEYWORD_PDF				= "pdf";
	public static final String	KEYWORD_COALITION		= "coalition";
	public static final String	KEYWORD_FUNCTION		= "function";
	public static final String	KEYWORD_POLIT_PARTY		= "politicalParty";
	public static final String	KEYWORD_PARLIAMENT		= "parliament";
	
	public static final String URL_BASE_ONLINE 	= "http://rozpocetmesta.sk/aplikacia_KE/"+PLACEHOLDER_STRING+".php";
	public static final String URL_BASE_OFFLINE = "json/"+PLACEHOLDER_STRING+".json";
	
	public static final String URL_MAYOR 		  = "mayor";
	public static final String URL_PLACES 		  = "places";
	public static final String URL_NEWS 		  = "connect";
	public static final String URL_CONTACTS 	  = "contacts";
	public static final String URL_OFFICE_BOARD	  = "connect_uradnatabula";
	public static final String URL_ATRIUM		  = "connect_atrium";
	public static final String URL_ATRIUM_PROGRAM = "program_atrium";
	public static final String URL_PARLIAMENT	  = "connect_parliament";
	
	
	/****************************** assets paths ******************************/
	public static final String INIT_DATA_ASSET = "data.json";
	
	
	/****************************** assets paths ******************************/
	public static final int GET_CONTENT_MAYOR    		 = 0; 
	public static final int GET_CONTENT_PLACES   		 = 1;
	public static final int GET_CONTENT_NEWS     		 = 2;
	public static final int GET_CONTENT_CONTACTS 		 = 3;
	public static final int GET_CONTENT_OFFICE_BOARD     = 4;
	public static final int GET_CONTENT_ATRIUM		     = 5;
	public static final int GET_CONTENT_PARLIAMENT	     = 6;
	public static final int GET_CONTENT_ATRIUM_PROGRAM   = 7;
	
	
	
	/****************************** delays ******************************/
	public static final int DELAY_1S = 1000;
	public static final int DELAY_2S = 2000;
	public static final int DELAY_3S = 3000;
	
}
