package com.example.demo.Kitchen;

public class Single implements ICookingStrategy {
    @Override
    public void execute() {
        System.out.println("Cooking a single pizza.");
        // Логіка приготування однієї піци
    }
}