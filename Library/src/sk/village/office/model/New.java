package sk.village.office.model;

import java.io.Serializable;

import sk.village.office.interfaces.IItem;

public class New implements IItem, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6632297429069044764L;
	private String title;
	private String londDesc;
	private String shortDesc;
	private String date;
	private String thumbnail;
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setLongDesc(String longDesc){
		this.londDesc = longDesc;
	}
	
	public String getLongDesc(){
		return londDesc;
	}
	
	public void setShortDesc(String shortDesc){
		this.shortDesc = shortDesc;
	}
	
	public String getShortDesc(){
		return shortDesc;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setThumbnail(String thb){
		this.thumbnail = thb;
	}
	
	public String getThumbnail(){
		return this.thumbnail;
	}
}
