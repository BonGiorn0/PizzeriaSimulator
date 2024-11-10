package com.example;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class PhysicalObjectImpl implements PhysicalObject {
    private Position position;
    private String state;
    private Rectangle rectangle;

    public PhysicalObjectImpl(double x, double y, double width, double height, Color color) {
        this.position = new Position(x, y);
        this.state = "inQueue";
        this.rectangle = new Rectangle(width, height, color);
        this.rectangle.setX(x);
        this.rectangle.setY(y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        this.rectangle.setX(position.getX());
        this.rectangle.setY(position.getY());
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void moveTo(Position newPosition) {
        setPosition(newPosition);
    }

    @Override
    public boolean hasArrived(Position targetPosition) {
        return this.position.equals(targetPosition);
    }

    @Override
    public void checkArrival(Position targetPosition) {
        if (hasArrived(targetPosition)) {
            setState("arrived");
        }
    }

    @Override
    public void render() {
        // Виведення на екран (використовуємо цей метод для візуалізації в JavaFX)
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
