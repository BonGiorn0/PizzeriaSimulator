package com.example.Restaurant;

import java.util.List;

public class Order implements IOrder{
	private String name;
	private double totalPrice;
	private List<IMenuItem> itemList;
	
	private double calculatePrice() {
		double price = 0;
		for(var item : itemList) {
			price += item.getPrice();
		}
		return price;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IMenuItem getMenuItem(int index) {
		try {
			return itemList.get(index);
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public void deleteMenuItem(int index) {
		try {
			itemList.remove(index);
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public double getTotalPrice() {
		totalPrice = calculatePrice();
		return totalPrice;
	}

	@Override
	public void add(IMenuItem item) {
		itemList.add(item);		
	}
}
