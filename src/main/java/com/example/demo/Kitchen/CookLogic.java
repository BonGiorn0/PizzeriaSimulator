package com.example.demo.Kitchen;

import com.example.demo.Restaurant.IOrderQueue;

public class CookLogic implements ICookLogic {
    private ICookingStrategy cookingStrategy;

    @Override
    public void takeOrderFromQueue(IOrderQueue orderQueue) {
        System.out.println("Taking an order from the queue.");
    }

    @Override
    public void doStep() {
        System.out.println("Executing a cooking step.");
        if (cookingStrategy != null) {
            cookingStrategy.execute();
        } else {
            System.out.println("No cooking strategy set.");
        }
    }

    @Override
    public void setCookingStrategy(ICookingStrategy strategy) {
        this.cookingStrategy = strategy;
        System.out.println("Cooking strategy set.");
    }
}