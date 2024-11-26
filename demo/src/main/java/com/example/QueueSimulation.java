package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QueueSimulation extends Application {
    private static final int RECT_SIZE = 50;

    private static final double SPEED = 1;  // Швидкість руху
    private static final double CASHIER_X = 500;  // X-координата каси
    private static final double CASHIER_Y = 400;  // Y-координата каси
    private static final double QUEUE_GAP = 70;  // Відстань між об'єктами в черзі
    private static final int ADD_INTERVAL = 3500;  // Інтервал додавання нових об'єктів (в мс)
    private static final int OBJECT_LIFETIME = 1500;  // Час до видалення об'єкта (5 секунд)

    private final List<IPhysicalObject> objects = new ArrayList<>();  // Список фізичних об'єктів
    private final Pane pane = new Pane();  // Панель для відображення
    private final RenderEngine renderEngine = new RenderEngine(pane);  // Рендеринг об'єктів
    @Override
    public void start(Stage stage) {

        // Анімація для оновлення позиції об'єктів у черзі
        Timeline moveTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> moveObjectsToQueue()));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        moveTimeline.play();

        // Анімація для додавання нового об'єкта кожні 1 секунду
        Timeline addTimeline = new Timeline(new KeyFrame(Duration.millis(ADD_INTERVAL), e -> addObjectToQueue()));
        addTimeline.setCycleCount(Timeline.INDEFINITE);
        addTimeline.play();

        Timeline cookTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> moveCooks()));
        cookTimeline.setCycleCount(Timeline.INDEFINITE);
        cookTimeline.play();
        // Сцена та налаштування
        addCooks();
  

        Scene scene = new Scene(pane, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Queue Simulation");
        stage.show();
    }
    String imagePath = new File("/home/artem/CrossPP/PizzeriaSimulator/demo/src/main/java/com/example/human.png").toURI().toString();

    // Додавання нового об'єкта до черги
    private void addObjectToQueue() {
        IPhysicalObject object = new PhysicalObjectImpl(-100, 400, RECT_SIZE, RECT_SIZE,imagePath);
        renderEngine.addObject(object);  // Додаємо об'єкт до renderEngine
        objects.add(object);  // Додаємо в список об'єктів
        System.out.println("Added new object at (50, 50)");
    }
    private final List<Cook> cooks = new ArrayList<>();

private void addCooks() {
    String cookImagePath = new File("/home/artem/CrossPP/PizzeriaSimulator/demo/src/main/java/com/example/cook.png").toURI().toString();

    for (int i = 0; i < 3; i++) {  // Example: 2 cooks
        Cook cook = new Cook(50 * i, 50 * i, RECT_SIZE, RECT_SIZE, cookImagePath);
        cooks.add(cook);
        renderEngine.addObject(cook);
    }
}

private void moveCooks() {
    for (Cook cook : cooks) {
        if (cook.isPaused()) continue;  // Skip paused cooks

        Position currentPos = cook.getPosition();
        Position targetPos = cook.getTargetOvenPosition();

        double dx = targetPos.getX() - currentPos.getX();
        double dy = targetPos.getY() - currentPos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > SPEED) {
            cook.moveTo(new Position(
                currentPos.getX() + SPEED * dx / distance,
                currentPos.getY() + SPEED * dy / distance
            ));
        } else {
            cook.moveTo(targetPos);  // Snap to target
            cook.pauseAtOven(2000, cook::moveToNextOven);  // Pause for 2 seconds at the oven
        }

        renderEngine.updateObjectPosition(cook, currentPos, cook.getPosition());
    }
}

    // Рух об'єктів до черги перед касою
    private void moveObjectsToQueue() {
        for (int i = 0; i < objects.size(); i++) {
            IPhysicalObject object = objects.get(i);
            Position oldPos = object.getPosition();

            // Координати цілі для кожного об'єкта
            double targetX = CASHIER_X - i * QUEUE_GAP;
            double targetY = CASHIER_Y;

            double dx = targetX - oldPos.getX();
            double dy = targetY - oldPos.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            // Якщо відстань більша за задану швидкість, рухаємось
            if (distance > SPEED) {
                object.moveTo(new Position(oldPos.getX() + SPEED * dx / distance, oldPos.getY() + SPEED * dy / distance));
            } else {
                object.moveTo(new Position(targetX, targetY));  // Якщо відстань менша за швидкість, досягаємо цілі
                // Якщо об'єкт досяг цілі, запускаємо таймер на видалення тільки для першого об'єкта
                if (i == 0) {
                    startObjectRemovalTimer(object);
                }
            }

            // Оновлення стану після прибуття
            object.checkArrival(new Position(targetX, targetY));

            Position newPos = object.getPosition();
            moveCooks();
            renderEngine.updateObjectPosition(object, oldPos, newPos);  // Оновлення позиції об'єкта
        }
    }

    // Запуск таймера для видалення об'єкта через 5 секунд після досягнення цілі
    private void startObjectRemovalTimer(IPhysicalObject object) {
        // Таймер для видалення об'єкта через 5 секунд після досягнення цілі
        PauseTransition pause = new PauseTransition(Duration.millis(OBJECT_LIFETIME)); // Пауза на 5 секунд
        pause.setOnFinished(event -> removeObject(object));  // Після паузи викликаємо метод для видалення
        pause.play();
    }

    // Видалення об'єкта з черги та панелі
    private void removeObject(IPhysicalObject object) {
        objects.remove(object);  // Видаляємо об'єкт з списку
        renderEngine.removeObject(object);  // Видаляємо об'єкт з панелі
        System.out.println("Object removed after 5 seconds");
    }

    // Основний метод для запуску програми
    public static void main(String[] args) {
        launch(args);
    }
}
