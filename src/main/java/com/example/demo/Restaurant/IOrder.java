package com.example.demo.Restaurant;

public interface IOrder {
    String getName();
    IMenuItem getMenuItem(int index);
    void deleteMenuItem(int index);
    double getTotalPrice();
    void add(IMenuItem item);
}