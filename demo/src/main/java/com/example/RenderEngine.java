package com.example;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class RenderEngine {
    private final Pane pane;
    private final Map<Position, Rectangle> objects;
    private final Map<Position, Image> images;

    public RenderEngine(Pane pane) {
        this.pane = pane;
        this.objects = new HashMap<>();
        this.images = new HashMap<>();
    }

    // Додати фізичний об'єкт на панель
    public void addObject(Position position, Rectangle object) {
        objects.put(position, object);
        pane.getChildren().add(object);
    }

    // Видалити об'єкт з панелі
    public void removeObject(Position position) {
        Rectangle rect = objects.remove(position);
        if (rect != null) {
            pane.getChildren().remove(rect);
        }
    }

    // Отримати зображення для заданої позиції
    public Image getImage(Position position) {
        return images.get(position);
    }

    // Оновити позицію об'єкта
    public void updateObjectPosition(Position oldPos, Position newPos) {
        Rectangle rect = objects.remove(oldPos);
        if (rect != null) {
            objects.put(newPos, rect);
            rect.setX(newPos.getX());
            rect.setY(newPos.getY());
        }
    }

    // Додавання зображення для певної позиції
    public void addImage(Position position, Image image) {
        images.put(position, image);
    }
}
