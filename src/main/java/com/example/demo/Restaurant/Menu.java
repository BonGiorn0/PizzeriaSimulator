package com.example.demo.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Menu implements IMenu {

    private List<MenuItem> itemList;

    public Menu() {
        itemList = new ArrayList<MenuItem>();
    }

    public void addItem(MenuItem item) {
        itemList.add(item);
    }

    @Override
    public MenuItem chooseItem(int index) {
        try {
            return itemList.get(index);
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return itemList;
    }
}