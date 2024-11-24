package com.example.Restaurant;

public class MenuItem implements IMenuItem{
	private String name;
	private double price;
	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public String getName() {
		return name;
	}
	
	
}
