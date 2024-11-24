package com.example;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import java.util.List;

public class RenderEngine {
    private Pane pane;
    private List<IPhysicalObject> objects;  // Список IPhysicalObject

    public RenderEngine(Pane pane) {
        this.pane = pane;
    }

    public void addObject(IPhysicalObject object) {
        // Додаємо ImageView замість Rectangle
        pane.getChildren().add(object.getRectangle());
    }

    public void updateObjectPosition(IPhysicalObject object, Position oldPos, Position newPos) {
        // Оновлення позиції ImageView
        object.getRectangle().setX(newPos.getX());
        object.getRectangle().setY(newPos.getY());
    }

    public void removeObject(IPhysicalObject object) {
        // Видалення ImageView
        pane.getChildren().remove(object.getRectangle());
    }
}
