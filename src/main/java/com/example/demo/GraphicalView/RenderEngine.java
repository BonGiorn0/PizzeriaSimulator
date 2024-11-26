package com.example.demo.GraphicalView;

import javafx.scene.layout.Pane;

import java.util.List;

public class RenderEngine {
    private Pane pane;
    private List<IPhysicalObject> objects;  // Змінили тип на IPhysicalObject

    public RenderEngine(Pane pane) {
        this.pane = pane;
    }

    // Додавання об'єкта
    public void addObject(IPhysicalObject object) {
        // Оскільки ми використовуємо IPhysicalObject, отримаємо прямокутник через геттер
        pane.getChildren().add(object.getRectangle());
    }

    // Оновлення позиції об'єкта
    public void updateObjectPosition(IPhysicalObject object, Position oldPos, Position newPos) {
        object.getRectangle().setX(newPos.getX());
        object.getRectangle().setY(newPos.getY());
    }

    // Видалення об'єкта
    public void removeObject(IPhysicalObject object) {
        pane.getChildren().remove(object.getRectangle());
    }
}
