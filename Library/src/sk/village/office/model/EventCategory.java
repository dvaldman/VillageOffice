package sk.village.office.model;

public class EventCategory {
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String string) {
		this.name = string;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof EventCategory))return false;
		return this.getName().equalsIgnoreCase(((EventCategory)o).getName());
	}
}
