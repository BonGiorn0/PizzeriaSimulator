package com.example.demo.Kitchen;

import com.example.demo.GraphicalView.IPhysicalObject;
import com.example.demo.Restaurant.IOrder;
import com.example.demo.Restaurant.IOrderQueue;

public class Cook {
    private IPhysicalObject physicalObject;
    private ICookLogic cookLogic;
    private IOrder order;
    private ICookingStrategy cookingStrategy;


    public Cook(ICookLogic cookLogic) {
        this.cookLogic = cookLogic;
    }

    public void setCookingStrategy(ICookingStrategy strategy) {
        this.cookingStrategy = strategy;
        cookLogic.setCookingStrategy(strategy);
    }

    public void processOrder(IOrderQueue orderQueue) {
        cookLogic.takeOrderFromQueue(orderQueue);
        cookLogic.doStep();
    }
}