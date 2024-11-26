package com.example.demo.Restaurant;

import com.example.demo.CustomerLogic.ICustomerLogic;

public interface ICashDesk {
    void takeOrder();
    void addCustomerToQueue(ICustomerLogic customerLogic);
    void putOrderInQueue();
    IMenu getMenu();
}