package com.datastructure.java11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
	private int id;
	private List<Item> items;
	
	
	private double cost;
	private double discount;
	private double totalCost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(Item item) {
		this.items.add(item);
	}
	
	public double getCost() {
		if(items.size()>0) {
			cost=items.stream().map(x->x.getPrice()).collect(Collectors.summingDouble(Double::doubleValue));
		}
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getDiscount() {
		if(items.size()>0) {
			discount=items.stream().map(x->(x.getPrice()*x.getCategory().getDiscount())/100).collect(Collectors.summingDouble(Double::doubleValue));
		}
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getTotalCost() {
		totalCost=cost-discount;
		return totalCost;
	}
	
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	public Cart() {
		items=new ArrayList<>();
	}
	
	
	
	
	
}
