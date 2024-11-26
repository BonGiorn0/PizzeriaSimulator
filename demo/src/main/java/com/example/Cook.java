package com.example;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Cook extends PhysicalObjectImpl {
    private int currentOvenIndex = 0;
    private boolean isPaused = false;
    public boolean isPaused() {
        return isPaused;
    }

    private static final Position[] OVEN_POSITIONS = {
    new Position(100, 100),
    new Position(300, 100),
    new Position(500, 100)
};

    public Cook(double x, double y, double width, double height, String imagePath) {
        super(x, y, width, height, imagePath);
    }

    public void moveToNextOven() {
        if (!isPaused) {
            currentOvenIndex = (currentOvenIndex + 1) % OVEN_POSITIONS.length;
        }
    }

    public Position getTargetOvenPosition() {
        return OVEN_POSITIONS[currentOvenIndex];
    }

    public void pauseAtOven(long delayMillis, Runnable onComplete) {
        isPaused = true;
        PauseTransition pause = new PauseTransition(Duration.millis(delayMillis));
        pause.setOnFinished(event -> {
            isPaused = false;
            onComplete.run();
        });
        pause.play();
    }
}
