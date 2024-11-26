package com.example.demo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Інтерфейс стратегії
interface ISpawnStrategy {
    Customer execute();
}
// Стратегія RushHourStrategy
class RushHourStrategy implements ISpawnStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Customer execute() {
        // Імітуємо генерацію клієнтів швидко (з мінімальною затримкою)
        try {
            int spawnDelay = 1000 + RANDOM.nextInt(1000); // Затримка від 1000 до 2000 мс (1 - 2 секунди)
            TimeUnit.MILLISECONDS.sleep(spawnDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Customer("RushHour Customer", "Texture_RushHour");
    }
}

// Стратегія NormalStrategy
class NormalStrategy implements ISpawnStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Customer execute() {
        // Імітуємо генерацію клієнтів із середнім інтервалом
        try {
            int spawnDelay = 5000 + RANDOM.nextInt(2000); // Затримка від 5000 до 7000 мс (5 - 7 секунд)
            TimeUnit.MILLISECONDS.sleep(spawnDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Customer("Normal Customer", "Texture_Normal");
    }
}

// Стратегія RandomDelayStrategy
class RandomDelayStrategy implements ISpawnStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Customer execute() {
        // Імітуємо випадкові затримки для створення клієнтів
        try {
            int spawnDelay = 2000 + RANDOM.nextInt(4000); // Затримка від 2000 до 6000 мс (2 - 6 секунд)
            TimeUnit.MILLISECONDS.sleep(spawnDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Customer("RandomDelay Customer", "Texture_Random");
    }
}

// Клас CustomerSpawner
class CustomerSpawner {
    private ISpawnStrategy spawnStrategy;

    public void setSpawnStrategy(ISpawnStrategy spawnStrategy) {
        this.spawnStrategy = spawnStrategy;
    }

    public Customer createCustomer() {
        if (spawnStrategy == null) {
            throw new IllegalStateException("Spawn strategy is not set.");
        }
        return spawnStrategy.execute();
    }
}
