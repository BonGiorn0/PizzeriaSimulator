package com.example.demo.Restaurant;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue implements IOrderQueue {
    private Queue<IOrder> queue;

    public OrderQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void putOrder(IOrder order) {
        queue.add(order);
    }

    @Override
    public IOrder getOrder() {
        return queue.poll(); // Повертаємо наступне замовлення (або null, якщо черга порожня)
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}