package com.example.demo.GraphicalView;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PhysicalObject implements IPhysicalObject {
    private Position position;
    private String state;
    private Rectangle rectangle;

    public PhysicalObject(double x, double y, double width, double height, Color color) {
        this.position = new Position(x, y);
        this.state = "inQueue";  // Початковий стан
        this.rectangle = new Rectangle(width, height, color);
        this.rectangle.setX(x);
        this.rectangle.setY(y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
        this.rectangle.setX(position.getX());
        this.rectangle.setY(position.getY());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void moveTo(Position newPosition) {
        setPosition(newPosition);  // Оновлюємо позицію
    }

    // Метод для перевірки, чи досяг об'єкт цільової позиції
    public boolean hasArrived(Position targetPosition) {
        return this.position.equals(targetPosition);
    }

    // Оновлюємо стан після досягнення позиції
    @Override
    public void checkArrival(Position targetPosition) {
        if (hasArrived(targetPosition)) {
            setState("arrived");  // Якщо досягнув, змінюємо стан
        }
    }

    // Оскільки JavaFX обробляє рендеринг без цього методу, він може бути порожнім
    public void render() {
        // Виведення на екран (реалізація вже через JavaFX)
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;  // Повертаємо прямокутник для рендерингу
    }

    // Реалізація методу setRectangle із інтерфейсу IPhysicalObject
    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;  // Встановлюємо новий прямокутник
    }
}