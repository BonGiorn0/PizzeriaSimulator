package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class QueueSimulation extends Application {
    private static final int RECT_SIZE = 20;
    private static final double SPEED = 2; // швидкість руху квадратів
    private static final double CASHIER_X = 300; // X-координата каси
    private static final double CASHIER_Y = 200; // Y-координата каси
    private static final double QUEUE_GAP = 25; // відстань між квадратами в черзі
    private static final int ADD_INTERVAL = 1000; // час інтервалу додавання (в мс)

    private final List<Rectangle> rectangles = new ArrayList<>(); // список квадратів
    private final Pane pane = new Pane();
    private final RenderEngine renderEngine = new RenderEngine(pane);

    @Override
    public void start(Stage stage) {

        // Налаштування анімації для оновлення положення квадратів у черзі
        Timeline moveTimeline = new Timeline(new KeyFrame(Duration.millis(30), e -> moveRectanglesToQueue()));
        moveTimeline.setCycleCount(Timeline.INDEFINITE);
        moveTimeline.play();

        // Налаштування анімації для додавання нового квадрата кожні 5 секунд
        Timeline addTimeline = new Timeline(new KeyFrame(Duration.millis(ADD_INTERVAL), e -> addRectangleToQueue()));
        addTimeline.setCycleCount(Timeline.INDEFINITE);
        addTimeline.play();

        // Додавання панелі до сцени
        Scene scene = new Scene(pane, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Queue Simulation");
        stage.show();
    }

    // Метод для додавання нового квадрата в кінець черги
    private void addRectangleToQueue() {
        Rectangle rect = new Rectangle(RECT_SIZE, RECT_SIZE, Color.BLUE);
        Position position = new Position(50, 50); // початкова позиція по X та Y
        renderEngine.addObject(position, rect);
        rectangles.add(rect);
        System.out.println("Added new rectangle at (50, 50)");
    }

    // Рух квадратів до черги перед касою
    private void moveRectanglesToQueue() {
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rect = rectangles.get(i);
            Position oldPos = new Position(rect.getX(), rect.getY());

            // Перевірка на null
            if (rect == null) {
                continue;
            }

            // Координати цілі для прямокутників (з права на ліво)
            double targetX = CASHIER_X - i * QUEUE_GAP; // Зменшуємо X на відстань QUEUE_GAP для кожного наступного
            double targetY = CASHIER_Y;

            double dx = targetX - rect.getX();
            double dy = targetY - rect.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance > SPEED) {
                // Рух до цілі
                rect.setX(rect.getX() + SPEED * dx / distance);
                rect.setY(rect.getY() + SPEED * dy / distance);
            } else {
                // Перший у черзі досягає точки і робить замовлення
                if (i == 0 && !("arrived".equals(rect.getUserData()))) {
                    rect.setUserData("arrived");
                    startRemoveTimer(rect);
                }
            }

            Position newPos = new Position(rect.getX(), rect.getY());
            renderEngine.updateObjectPosition(oldPos, newPos);

            System.out.printf("Rectangle at (%f, %f) moving towards (%f, %f)%n", rect.getX(), rect.getY(), targetX, targetY);
        }
    }

    // Запуск таймера на видалення для першого квадрату після прибуття
    private void startRemoveTimer(Rectangle rect) {
        Timeline removeTimer = new Timeline(new KeyFrame(Duration.millis(5000), e -> {
            if (rectangles.contains(rect)) {
                rectangles.remove(rect);
                renderEngine.removeObject(new Position(rect.getX(), rect.getY()));
                System.out.println("Removed rectangle from queue after arrival");
            }
        }));
        removeTimer.setCycleCount(1);
        removeTimer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
