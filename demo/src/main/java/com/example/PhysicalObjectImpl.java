package com.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhysicalObjectImpl implements IPhysicalObject {
    private Position position;
    private String state;
    private ImageView imageView;

    public PhysicalObjectImpl(double x, double y, double width, double height, String imagePath) {
        this.position = new Position(x, y);
        this.state = "inQueue";

        // Завантаження зображення
        Image image = new Image(imagePath);
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(width);  // Встановлюємо ширину
        this.imageView.setFitHeight(height);  // Встановлюємо висоту
        this.imageView.setX(x);  // Початкова координата X
        this.imageView.setY(y);  // Початкова координата Y
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        this.imageView.setX(position.getX());
        this.imageView.setY(position.getY());
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
        // Можливо, візуалізація вже автоматична через ImageView
    }

    @Override
    public ImageView getRectangle() {  // Метод тепер повертає ImageView
        return imageView;
    }

    @Override
    public void setRectangle(ImageView rectangle) {  // Змінюємо параметр на ImageView
        this.imageView = rectangle;
    }
}
