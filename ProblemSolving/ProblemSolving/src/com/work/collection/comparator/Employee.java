package com.work.collection.comparator;

public class Employee {

	private int id;
	private String name;
	private String officeName;
	
	

	public Employee(int id, String name, String officeName) {
		super();
		this.id = id;
		this.name = name;
		this.officeName = officeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", officeName=" + officeName + "]";
	}
	
	

}
