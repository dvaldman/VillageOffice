package sk.village.office.model;

import java.io.Serializable;

import sk.village.office.interfaces.IItem;

public class Member implements IItem, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1899244984165614979L;
	private String name;
	private String age;
	private String job;
	private String voteLocation;
	private String thumbnail;
	private String timeInterval;
	private String email;
	private String coalition;
	private String function;
	private String party;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getVoteLocation() {
		return voteLocation;
	}
	public void setVoteLocation(String voteLocation) {
		this.voteLocation = voteLocation;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCoalition() {
		return coalition;
	}
	public void setCoalition(String coalition) {
		this.coalition = coalition;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	
	

}
