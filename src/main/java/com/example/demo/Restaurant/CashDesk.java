package com.example.demo.Restaurant;

import com.example.demo.CustomerLogic.ICustomerLogic;

import java.util.Queue;

public class CashDesk implements ICashDesk {

    private Queue<ICustomerLogic> queue;
    private IMenu menu;
    private IOrder order;

    public CashDesk(Queue<ICustomerLogic> queue, IMenu menu) {
        this.queue = queue;
        this.menu = menu;
        order = new Order();
    }

    public Queue<ICustomerLogic> getQueue() {
        return queue;
    }

    public IOrder getOrder() {
        return order;
    }

    @Override
    public IMenu getMenu() {
        return menu;
    }

    @Override
    public void takeOrder() {
        if(queue.isEmpty()) return;

        order = queue.poll().chooseOrder(menu);
    }

    @Override
    public void addCustomerToQueue(ICustomerLogic customerLogic) {
        queue.add(customerLogic);
    }

    @Override
    public void putOrderInQueue() {

        // TODO there is an OrderQueue object somewhere. Put the order in that queue.
        // TODO set the order name (check number?) there
    }
}
