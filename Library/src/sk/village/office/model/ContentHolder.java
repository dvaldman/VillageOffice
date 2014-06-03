package sk.village.office.model;

import java.util.ArrayList;
import java.util.List;

import sk.village.office.core.Configuration;
import sk.village.office.db.DBHelper;
import sk.village.office.db.Tables;
import sk.village.office.util.Log;
import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.maps.model.LatLng;

public class ContentHolder {
	
	private List<Place> placesType1;
	private List<Place> placesType2;
	private List<Place> placesType3;
	private List<Place> placesType4;
	private List<Place> placesType5;
	
	private List<New> news;
	private List<New> officeBoard;
	private List<New> atrium;
	
	private List<Member> members;
	
	private static ContentHolder instance;
	private static Context context;
	
	private ContentHolder(Context cont){
		context = cont;
	}
	
	public static ContentHolder getInstance(Context context){
		if(instance == null)
			instance = new ContentHolder(context);
		return instance;
	}
	
	public List<Place> getPlaces1(){
		if(placesType1 == null)
			placesType1 = initPlaces(Configuration.placesCategory1);
		return placesType1;
	}
	
	public List<Place> getPlaces2(){
		if(placesType2 == null)
			placesType2 = initPlaces(Configuration.placesCategory2);
		return placesType2;
	}
	
	public List<Place> getPlaces3(){
		if(placesType3 == null)
			placesType3 = initPlaces(Configuration.placesCategory3);
		return placesType3;
	}
	
	public List<Place> getPlaces4(){
		if(placesType4 == null)
			placesType4 = initPlaces(Configuration.placesCategory4);
		return placesType4;
	}
	
	
	public List<Place> initPlaces(String cat){
		
		List<Place> places = new ArrayList<Place>();
		
		Cursor c = DBHelper.getInstance(context).getResultsFromSingleTable(
        		Tables.Places.TABLE_NAME,
        		new String[]{Tables.Places.CATEGORY},
        		new String[]{cat});
		Log.i("adding "+c.getCount()+" places into "+cat);	
        Place place = null;
        if(c.moveToFirst()){
        	do{
	        	place = new Place();
	        	place.setName(c.getString(c.getColumnIndex(Tables.Places.NAME)));
	    		
	    		place.setDescription(c.getString(c.getColumnIndex(Tables.Places.DESCRIPTION)));
	    		LatLng gps = new LatLng(Double.parseDouble(c.getString(c.getColumnIndex(Tables.Places.LATITUDE))), Double.parseDouble(c.getString(c.getColumnIndex(Tables.Places.LONGITUDE))));
	    		place.setGps(gps);
	    		place.setCategory(cat);
	    		places.add(place);
        	}while(c.moveToNext());
    	}
        return places;
	}
	
	public List<New> getNews(){
		if(news == null)
			news = initNews();
		return news;
	}
	
	public List<New> initNews(){
		
		List<New> news = new ArrayList<New>();
		
		Cursor c = DBHelper.getInstance(context).getResultsFromSingleTable(
        		Tables.News.TABLE_NAME,
        		null,
        		null);
		Log.i("adding "+c.getCount()+" news");	
        New newThing = null;
        if(c.moveToFirst()){
        	do{
	        	newThing = new New();
	        	newThing.setTitle(c.getString(c.getColumnIndex(Tables.News.TITLE)));
	    		newThing.setShortDesc(c.getString(c.getColumnIndex(Tables.News.SHORT_DESC)));
	    		newThing.setLongDesc(c.getString(c.getColumnIndex(Tables.News.LONG_DESC)));
	    		newThing.setDate(c.getString(c.getColumnIndex(Tables.News.DATE)));
	    		newThing.setThumbnail(c.getString(c.getColumnIndex(Tables.News.IMAGE)));
	    		news.add(newThing);
        	}while(c.moveToNext());
    	}
        return news;
	}
	
	public List<New> getOfficeBoard(){
		if(officeBoard == null)
			officeBoard = initOfficeBoard();
		return officeBoard;
	}
	
	public List<New> initOfficeBoard(){
		
		List<New> news = new ArrayList<New>();
		
		Cursor c = DBHelper.getInstance(context).getResultsFromSingleTable(
        		Tables.OfficeBoard.TABLE_NAME,
        		null,
        		null);
		
        New newThing = null;
        if(c.moveToFirst()){
        	do{
	        	newThing = new New();
	        	newThing.setTitle(c.getString(c.getColumnIndex(Tables.OfficeBoard.TITLE)));
	    		newThing.setShortDesc(c.getString(c.getColumnIndex(Tables.OfficeBoard.SHORT_DESC)));
	    		newThing.setLongDesc(c.getString(c.getColumnIndex(Tables.OfficeBoard.LONG_DESC)));
	    		newThing.setDate(c.getString(c.getColumnIndex(Tables.OfficeBoard.DATE)));
	    		newThing.setThumbnail(c.getString(c.getColumnIndex(Tables.OfficeBoard.IMAGE)));
	    		news.add(newThing);
        	}while(c.moveToNext());
    	}
        return news;
	}
	
	public List<New> getAtrium(){
		if(atrium == null)
			atrium = initAtrium();
		return atrium;
	}
	
	public List<New> initAtrium(){
		
		List<New> news = new ArrayList<New>();
		
		Cursor c = DBHelper.getInstance(context).getResultsFromSingleTable(
        		Tables.Atrium.TABLE_NAME,
        		null,
        		null);
		
        New newThing = null;
        if(c.moveToFirst()){
        	do{
	        	newThing = new New();
	        	newThing.setTitle(c.getString(c.getColumnIndex(Tables.Atrium.TITLE)));
	    		newThing.setShortDesc(c.getString(c.getColumnIndex(Tables.Atrium.SHORT_DESC)));
	    		newThing.setLongDesc(c.getString(c.getColumnIndex(Tables.Atrium.LONG_DESC)));
	    		newThing.setDate(c.getString(c.getColumnIndex(Tables.Atrium.DATE)));
	    		newThing.setThumbnail(c.getString(c.getColumnIndex(Tables.Atrium.IMAGE)));
	    		news.add(newThing);
        	}while(c.moveToNext());
    	}
        return news;
	}
	
	public List<Member> getMembers(){
		if(members == null)
			members = initMembers();
		return members;
	}
	
	public List<Member> initMembers(){
		
		List<Member> members = new ArrayList<Member>();
		
		Cursor c = DBHelper.getInstance(context).getResultsFromSingleTable(
        		Tables.Parliament.TABLE_NAME,
        		null,
        		null);
			
		Log.i("adding "+c.getCount()+" members");	
        Member newMember = null;
        if(c.moveToFirst()){
        	do{
	        	newMember = new Member();
	        	newMember.setName(c.getString(c.getColumnIndex(Tables.Parliament.NAME)));
	        	newMember.setAge(c.getString(c.getColumnIndex(Tables.Parliament.AGE)));
	        	newMember.setJob(c.getString(c.getColumnIndex(Tables.Parliament.JOB)));
	        	newMember.setVoteLocation(c.getString(c.getColumnIndex(Tables.Parliament.VOTE_LOCATION)));
	        	newMember.setTimeInterval(c.getString(c.getColumnIndex(Tables.Parliament.TIME_INTERVAL)));
	        	newMember.setEmail(c.getString(c.getColumnIndex(Tables.Parliament.EMAIL)));
	        	newMember.setCoalition(c.getString(c.getColumnIndex(Tables.Parliament.COALITION)));
	        	newMember.setFunction(c.getString(c.getColumnIndex(Tables.Parliament.FUNCTION)));
	        	newMember.setThumbnail(c.getString(c.getColumnIndex(Tables.Parliament.IMAGE)));
	        	newMember.setParty(c.getString(c.getColumnIndex(Tables.Parliament.PARTY)));
	        	members.add(newMember);
        	}while(c.moveToNext());
    	}
        return members;
	}
	

}
