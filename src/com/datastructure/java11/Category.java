package com.datastructure.java11;

public class Category {
	
	private int id;
	private String name;
	private float discount;
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
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Category(int id, String name, float discount) {
		super();
		this.id = id;
		this.name = name;
		this.discount = discount;
	}
	
	

}
