package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QueueSimulation extends Application {
    private static final int RECT_SIZE = 50;

    private static final double SPEED = 10; // Швидкість руху
    private static final double CASHIER_X = 150; // X-координата каси
    private static final double CASHIER_Y = 300; // Y-координата каси
    private static final double COOK_X = 150; // X-координата каси
    private static final double COOK_Y = 200; // Y-координата каси
    private static final double QUEUE_GAP = 70; // Відстань між об'єктами в черзі
    private static final double SECOND_QUEUE_X = 150; // X-координата другої черги
    private static final double SECOND_QUEUE_Y = 480; // Y-координата другої черги

    private static final int ADD_INTERVAL = 500; // Інтервал додавання нових об'єктів (в мс)
    private static int MAX_QUEUE_SIZE = 3; // Максимальна кількість людей у черзі

    private final List<IPhysicalObject> firstQueue = new ArrayList<>(); // Список для першої черги
    private final List<IPhysicalObject> secondQueue = new ArrayList<>(); // Список для другої черги
    private final List<Cook> cooks = new ArrayList<>(); // Список кухарів

    private final Pane pane = new Pane(); // Панель для відображення
    private final RenderEngine renderEngine = new RenderEngine(pane); // Рендеринг об'єктів

    private final String backgroundImagePath = new File("C:\\Users\\38068\\Documents\\GitHub\\PizzeriaSimulator\\demo\\src\\main\\java\\com\\example\\fon.png").toURI().toString();
    private final String imagePath = new File("C:\\Users\\38068\\Documents\\GitHub\\PizzeriaSimulator\\demo\\src\\main\\java\\com\\example\\human.png").toURI().toString();

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

        // Додавання таймера
        startTimer();

        // Анімація для кухарів
        Timeline cookTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> moveCooks()));
        cookTimeline.setCycleCount(Timeline.INDEFINITE);
        cookTimeline.play();

        // Додаємо кухарів
        addCooks();

        Scene scene = new Scene(pane, 720, 600);
        stage.setScene(scene);
        stage.setTitle("Queue Simulation");
        Timeline checkOrderTimeline = new Timeline(new KeyFrame(Duration.millis(500), e -> checkAndRemoveFromSecondQueue()));
        checkOrderTimeline.setCycleCount(Timeline.INDEFINITE);
        checkOrderTimeline.play();

        stage.show();
    }

    private void startTimer() {
        Timeline timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            System.out.println("Simulation running...");
        }));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();
    }

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

    private void addCooks() {
        String cookImagePath = new File("C:\\Users\\38068\\Documents\\GitHub\\PizzeriaSimulator\\demo\\src\\main\\java\\com\\example\\cook.png").toURI().toString();

        for (int i = 0; i < 3; i++) {
            Cook cook = new Cook(50 * i, 50 * i, COOK_X, COOK_Y, cookImagePath);
            cooks.add(cook);
            renderEngine.addObject(cook);
        }
    }

    private void moveCooks() {
        for (Cook cook : cooks) {
            if (cook.isPaused()) continue;

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
                cook.moveTo(targetPos); // Snap to target
                cook.pauseAtOven(2000, cook::moveToNextOven); // Pause for 2 seconds at the oven
            }

            renderEngine.updateObjectPosition(cook, currentPos, cook.getPosition());
        }
    }

    private void moveObjectsToQueue(List<IPhysicalObject> queue, double targetX, double targetY, boolean moveToSecondQueue) {
        for (int i = 0; i < queue.size(); i++) {
            IPhysicalObject object = queue.get(i);
            Position oldPos = object.getPosition();

            double targetPosX = targetX - i * QUEUE_GAP;
            double targetPosY = targetY;

            double dx = targetPosX - oldPos.getX();
            double dy = targetPosY - oldPos.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > SPEED) {
                object.moveTo(new Position(oldPos.getX() + SPEED * dx / distance, oldPos.getY() + SPEED * dy / distance));
            } else {
                object.moveTo(new Position(targetPosX, targetPosY));
                if (moveToSecondQueue && i == 0) {
                    moveToSecondQueue(object);
                }
            }

            Position newPos = object.getPosition();
            renderEngine.updateObjectPosition(object, oldPos, newPos);
        }
    }
    private void checkAndRemoveFromSecondQueue() {
        if (!secondQueue.isEmpty()) {
            IPhysicalObject firstInQueue = secondQueue.get(0);

            if (firstInQueue instanceof PhysicalObjectImpl) {
                PhysicalObjectImpl client = (PhysicalObjectImpl) firstInQueue;

                // Симуляція готовності замовлення
                if (new Random().nextInt(100) < 10) { // 10% шанс готовності
                    client.setOrderReady(true);
                }

                if (client.isOrderReady()) {
                    System.out.println("Order is ready for the first client in the second queue.");

                    // Видалення клієнта із другої черги
                    secondQueue.remove(client);
                    renderEngine.removeObject(client); // Видалення об'єкта з екрану
                }
            }
        }
    }

    private void moveToSecondQueue(IPhysicalObject object) {
        if (secondQueue.size() >= MAX_QUEUE_SIZE) {
            System.out.println("Second queue is full. Cannot move object.");
            return;
        }
        firstQueue.remove(object);
        secondQueue.add(object);
        System.out.println("Moved object to second queue");
    }



    public static void main(String[] args) {
        launch(args);
    }
}
