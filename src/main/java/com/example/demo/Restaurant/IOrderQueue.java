package com.example.demo.Restaurant;

public interface IOrderQueue {
    void putOrder(IOrder order);
    IOrder getOrder();
    boolean isEmpty();
}