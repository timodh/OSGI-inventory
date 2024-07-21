package com.inventorymanagementPublisher;

import java.util.Comparator;

public class ItemDTO implements Comparator<ItemDTO>{

	private int id;
	private String name;
	private int quantity;
	private double price;
	
	public ItemDTO(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int compare(ItemDTO o1, ItemDTO o2) {
		return Integer.compare(o1.getId(), o2.getId());
	}
	
	
	
}
