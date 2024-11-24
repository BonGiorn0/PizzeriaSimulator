package com.example.Restaurant;

public interface IOrder {
	String getName();
	MenuItem getMenuItem(int index);
	void deleteMenuItem(int index);
	double getTotalPrice();
}
