package com.example.demo;

public class Cook {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void prepareOrder() {
        System.out.println("Cook " + name + " is preparing the order.");
    }
}
