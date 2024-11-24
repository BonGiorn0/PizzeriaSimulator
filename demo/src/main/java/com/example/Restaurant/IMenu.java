package com.example.Restaurant;

import java.util.List;

public interface IMenu {
	MenuItem chooseItem(int index);
	List<MenuItem> getMenuItems();
	void addItem(MenuItem item);
}
