package com.example;

public class Oven {
    private final Position position;
    private boolean isBusy = false;
    private Order currentOrder;
    private long cookingTime = 5000; // Час приготування (в мс)
    private long startTime;

    public Oven(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void startCooking(Order order) {
        if (isBusy) return;
        this.currentOrder = order;
        this.isBusy = true;
        this.startTime = System.currentTimeMillis();
    }

    public void update() {
        if (isBusy && System.currentTimeMillis() - startTime >= cookingTime) {
            isBusy = false;
            if (currentOrder != null) {
                currentOrder.complete();
                System.out.println("Order " + currentOrder.getPizzaType() + " is ready!");
            }
        }
    }
}
