package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QueueSimulation extends Application {
    private static final int RECT_SIZE = 50;

    private static final double SPEED = 10; // Швидкість руху
    private static final double CASHIER_X = 150; // X-координата каси
    private static final double CASHIER_Y = 300; // Y-координата каси
    private static final double QUEUE_GAP = 70; // Відстань між об'єктами в черзі
    private static final double SECOND_QUEUE_X = 150; // X-координата другої черги
    private static final double SECOND_QUEUE_Y = 480; // Y-координата другої черги

    private static final int ADD_INTERVAL = 500; // Інтервал додавання нових об'єктів (в мс)
    private static final int MAX_QUEUE_SIZE = 3; // Максимальна кількість людей у черзі

    private final List<IPhysicalObject> firstQueue = new ArrayList<>(); // Список для першої черги
    private final List<IPhysicalObject> secondQueue = new ArrayList<>(); // Список для другої черги
    private final Pane pane = new Pane(); // Панель для відображення
    private final RenderEngine renderEngine = new RenderEngine(pane); // Рендеринг об'єктів

    private final String backgroundImagePath = new File("C:\\Users\\38068\\Documents\\GitHub\\PizzeriaSimulator\\demo\\src\\main\\java\\com\\example\\fon.png").toURI().toString();
    private final String imagePath = new File("C:\\Users\\38068\\Desktop\\human.PNG").toURI().toString();

    @Override
    public void start(Stage stage) {
        // Додаємо фонову картинку
        Image backgroundImage = new Image(backgroundImagePath);
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(720);
        backgroundView.setFitHeight(600);
        backgroundView.setPreserveRatio(false);
        pane.getChildren().add(backgroundView); // Додаємо фонове зображення до Pane

        // Анімація для оновлення позиції об'єктів у черзі
        Timeline moveTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> {
            moveObjectsToQueue(firstQueue, CASHIER_X, CASHIER_Y, true); // Рух до першої черги
            moveObjectsToQueue(secondQueue, SECOND_QUEUE_X, SECOND_QUEUE_Y, false); // Рух до другої черги
        }));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        moveTimeline.play();

        // Анімація для додавання нового об'єкта кожні 1 секунду
        Timeline addTimeline = new Timeline(new KeyFrame(Duration.millis(ADD_INTERVAL), e -> addObjectToQueue()));
        addTimeline.setCycleCount(Timeline.INDEFINITE);
        addTimeline.play();

        // Сцена та налаштування
        Scene scene = new Scene(pane, 720, 600);
        stage.setScene(scene);
        stage.setTitle("Queue Simulation");
        stage.show();
    }

    // Додавання нового об'єкта до черги
    private void addObjectToQueue() {
        if (firstQueue.size() >= MAX_QUEUE_SIZE) {
            System.out.println("First queue is full. Cannot add new object.");
            return; // Не додаємо нові об'єкти, якщо черга заповнена
        }

        IPhysicalObject object = new PhysicalObjectImpl(-100, 300, RECT_SIZE, RECT_SIZE, imagePath);
        renderEngine.addObject(object); // Додаємо об'єкт до renderEngine
        firstQueue.add(object); // Додаємо в список першої черги
        System.out.println("Added new object to the first queue");
    }

    // Рух об'єктів до заданої черги
    private void moveObjectsToQueue(List<IPhysicalObject> queue, double targetX, double targetY, boolean moveToSecondQueue) {
        for (int i = 0; i < queue.size(); i++) {
            IPhysicalObject object = queue.get(i);
            Position oldPos = object.getPosition();

            // Координати цілі для кожного об'єкта
            double targetPosX = targetX - i * QUEUE_GAP;
            double targetPosY = targetY;

            double dx = targetPosX - oldPos.getX();
            double dy = targetPosY - oldPos.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            // Якщо відстань більша за задану швидкість, рухаємось
            if (distance > SPEED) {
                object.moveTo(new Position(oldPos.getX() + SPEED * dx / distance, oldPos.getY() + SPEED * dy / distance));
            } else {
                object.moveTo(new Position(targetPosX, targetPosY)); // Якщо відстань менша за швидкість, досягаємо цілі
                if (moveToSecondQueue && i == 0) {
                    moveToSecondQueue(object); // Переміщення до другої черги
                }
            }

            Position newPos = object.getPosition();
            renderEngine.updateObjectPosition(object, oldPos, newPos); // Оновлення позиції об'єкта
        }
    }

    // Переміщення об'єкта до другої черги
    private void moveToSecondQueue(IPhysicalObject object) {
        if (secondQueue.size() >= MAX_QUEUE_SIZE) {
            System.out.println("First queue is full. Cannot add new object.");
            return; // Не додаємо нові об'єкти, якщо черга заповнена
        }
        firstQueue.remove(object);

        secondQueue.add(object);
        System.out.println("Moved object to second queue");
    }

    // Основний метод для запуску програми
    public static void main(String[] args) {
        launch(args);
    }
}
