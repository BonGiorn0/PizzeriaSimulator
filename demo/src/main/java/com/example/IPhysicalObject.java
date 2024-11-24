package com.example;

import javafx.scene.image.ImageView;

public interface IPhysicalObject {
    Position getPosition();

    void setPosition(Position position);

    String getState();

    void setState(String state);

    void moveTo(Position newPosition);

    boolean hasArrived(Position targetPosition);

    void checkArrival(Position targetPosition);

    void render();

    ImageView getRectangle();  // Замінити Rectangle на ImageView

    void setRectangle(ImageView rectangle);  // Замінити Rectangle на ImageView
}
