package sk.village.office.model;

import sk.village.office.interfaces.IItem;
import com.google.android.gms.maps.model.LatLng;

public class Place implements IItem {
	private String name;
	private String description;
	private int category_id;
	private String category;
	private LatLng gps;
	private String address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategoryId() {
		return category_id;
	}
	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public LatLng getGps() {
		return gps;
	}
	public void setGps(LatLng gps) {
		this.gps = gps;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
