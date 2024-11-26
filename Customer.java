package com.example.demo;

public class Customer {
    private String name;
    private String texture;

    public Customer(String name, String texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public String getTexture() {
        return texture;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', texture='" + texture + "'}";
    }
}
