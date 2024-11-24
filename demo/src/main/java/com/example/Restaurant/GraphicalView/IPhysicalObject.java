package com.example.Restaurant.GraphicalView;

import javafx.scene.shape.Rectangle;

public interface IPhysicalObject {
    Position getPosition();
    void moveTo(Position newPosition);
    void checkArrival(Position target);
    void setRectangle(Rectangle rectangle);
    Rectangle getRectangle();
}

