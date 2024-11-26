package com.example.demo.Kitchen;

import com.example.demo.Restaurant.IOrderQueue;

public interface ICookLogic {
    void takeOrderFromQueue(IOrderQueue orderQueue);
    void doStep();
    void setCookingStrategy(ICookingStrategy strategy);
}