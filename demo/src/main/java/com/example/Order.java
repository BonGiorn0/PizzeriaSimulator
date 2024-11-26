package com.example;

public class Order {
    private final String pizzaType; // Тип піци
    private boolean isCompleted = false;

    public Order(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void complete() {
        isCompleted = true;
    }
}
