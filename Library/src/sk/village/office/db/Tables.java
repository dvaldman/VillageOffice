package sk.village.office.db;

import sk.village.office.core.Constants;


public class Tables
{
	
	public static class Mayor
	{
		public static final String	TABLE_NAME		= "Mayor";
		
		public static final String	NAME			= TABLE_NAME + "_" + Constants.KEYWORD_NAME;
		public static final String	TEL_NUM			= TABLE_NAME + "_" + Constants.KEYWORD_TEL_NUM;
		public static final String	MAIL			= TABLE_NAME + "_" + Constants.KEYWORD_MAIL;
		public static final String	DESCRIPTION		= TABLE_NAME + "_" + Constants.KEYWORD_DESCRIPTION;

		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + NAME + " TEXT, " + 
																							TEL_NUM + " TEXT, "+ 
																							MAIL + " TEXT, "+
																							DESCRIPTION + " TEXT "+
																						");";
	}
	
	public static class Places
	{
		public static final String	TABLE_NAME		= "Places";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	NAME			= TABLE_NAME + "_" + Constants.KEYWORD_NAME;
		public static final String	LONGITUDE		= TABLE_NAME + "_" + Constants.KEYWORD_LONGITUDE;
		public static final String	LATITUDE		= TABLE_NAME + "_" + Constants.KEYWORD_LATITUDE;
		public static final String	CATEGORY		= TABLE_NAME + "_" + Constants.KEYWORD_CATEGORY;
		public static final String	DESCRIPTION		= TABLE_NAME + "_" + Constants.KEYWORD_DESCRIPTION;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							NAME + " TEXT, "+
																							LONGITUDE + " TEXT, "+
																							LATITUDE + " TEXT, "+
																							CATEGORY + " TEXT, "+
																							DESCRIPTION + " TEXT "+
																						");";
	}
	
	public static class Categories
	{
		public static final String	TABLE_NAME		= "Categories";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	NAME			= TABLE_NAME + "_" + Constants.KEYWORD_NAME;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							NAME + " TEXT "+ 
																						");";
	}
	
	
	
	
	public static class News
	{
		public static final String	TABLE_NAME		= "News";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	TITLE			= TABLE_NAME + "_" + Constants.KEYWORD_TITLE;
		public static final String	DATE			= TABLE_NAME + "_" + Constants.KEYWORD_DATE;
		public static final String	LONG_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_LONG_DESC;
		public static final String	SHORT_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_SHORT_DESC;
		public static final String	IMAGE			= TABLE_NAME + "_" + Constants.KEYWORD_IMAGE_THB;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							TITLE + " TEXT, " +
																							DATE + " TEXT, " +
																							LONG_DESC + " TEXT, " +
																							SHORT_DESC + " TEXT, " +
																							IMAGE + " TEXT " +
																						");";
	}
	
	public static class OfficeBoard
	{
		public static final String	TABLE_NAME		= "OfficeBoard";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	TITLE			= TABLE_NAME + "_" + Constants.KEYWORD_TITLE;
		public static final String	DATE			= TABLE_NAME + "_" + Constants.KEYWORD_DATE;
		public static final String	LONG_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_LONG_DESC;
		public static final String	SHORT_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_SHORT_DESC;
		public static final String	IMAGE			= TABLE_NAME + "_" + Constants.KEYWORD_IMAGE_THB;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							TITLE + " TEXT, " +
																							DATE + " TEXT, " +
																							LONG_DESC + " TEXT, " +
																							SHORT_DESC + " TEXT, " +
																							IMAGE + " TEXT " +
																						");";
	}
	
	public static class Atrium
	{
		public static final String	TABLE_NAME		= "Atrium";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	TITLE			= TABLE_NAME + "_" + Constants.KEYWORD_TITLE;
		public static final String	DATE			= TABLE_NAME + "_" + Constants.KEYWORD_DATE;
		public static final String	LONG_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_LONG_DESC;
		public static final String	SHORT_DESC		= TABLE_NAME + "_" + Constants.KEYWORD_SHORT_DESC;
		public static final String	IMAGE			= TABLE_NAME + "_" + Constants.KEYWORD_IMAGE_THB;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							TITLE + " TEXT, " +
																							DATE + " TEXT, " +
																							LONG_DESC + " TEXT, " +
																							SHORT_DESC + " TEXT, " +
																							IMAGE + " TEXT " +
																						");";
	}
	
	public static class Parliament
	{
		public static final String	TABLE_NAME		= "Parliament";
		
		public static final String	ID				= TABLE_NAME + "_" + Constants.KEYWORD_ID;
		public static final String	NAME			= TABLE_NAME + "_" + Constants.KEYWORD_NAME;
		public static final String	AGE				= TABLE_NAME + "_" + Constants.KEYWORD_AGE;
		public static final String	JOB				= TABLE_NAME + "_" + Constants.KEYWORD_JOB;
		public static final String	VOTE_LOCATION	= TABLE_NAME + "_" + Constants.KEYWORD_VOTE_LOCATION;
		public static final String	IMAGE			= TABLE_NAME + "_" + Constants.KEYWORD_IMAGE_THB;
		public static final String	TIME_INTERVAL	= TABLE_NAME + "_" + Constants.KEYWORD_TIME_INTERVAL;
		public static final String	EMAIL			= TABLE_NAME + "_" + Constants.KEYWORD_EMAIL;
		public static final String	COALITION		= TABLE_NAME + "_" + Constants.KEYWORD_COALITION;
		public static final String	FUNCTION		= TABLE_NAME + "_" + Constants.KEYWORD_FUNCTION;
		public static final String	PARTY			= TABLE_NAME + "_" + Constants.KEYWORD_POLIT_PARTY;
		
		public static final String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + 
																							NAME + " TEXT, " +
																							AGE + " TEXT, " +
																							JOB + " TEXT, " +
																							VOTE_LOCATION + " TEXT, " +
																							IMAGE + " TEXT, " +
																							TIME_INTERVAL + " TEXT, " +
																							EMAIL + " TEXT, " +
																							COALITION + " TEXT, " +
																							FUNCTION + " TEXT, " +
																							PARTY + " TEXT " +
																						");";
	}
	
	
}
