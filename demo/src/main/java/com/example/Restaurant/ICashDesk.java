package com.example.Restaurant;

import com.example.CustomerLogic.ICustomerLogic;

public interface ICashDesk {
	void takeOrder();
	void addCustomerToQueue(ICustomerLogic customerLogic);
	void putOrderInQueue();
}
