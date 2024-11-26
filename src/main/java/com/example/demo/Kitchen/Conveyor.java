package com.example.demo.Kitchen;

public class Conveyor implements ICookingStrategy {
    @Override
    public void execute() {
        System.out.println("Cooking pizzas on the conveyor.");
        // Логіка приготування конвеєром
    }
}