package com.example;

public interface PhysicalObject {

    // Отримати поточну позицію об'єкта
    Position getPosition();

    // Встановити нову позицію для об'єкта
    void setPosition(Position position);

    // Отримати стан об'єкта
    String getState();

    // Встановити новий стан об'єкта
    void setState(String state);

    // Рухатися до нової позиції
    void moveTo(Position newPosition);

    // Перевірити, чи досягнув об'єкт цільової позиції
    boolean hasArrived(Position targetPosition);

    // Оновлення стану об'єкта, якщо він досягнув цілі
    void checkArrival(Position targetPosition);

    // Візуалізувати об'єкт на екран (наприклад, створити прямокутник для відображення)
    void render();
}
