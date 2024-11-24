package com.example.Customers.CustomerLogic;

import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IMenu;

public interface ICustomerLogic {
	void placeOrder(IMenu menu);
	void chooseCashDesk(ICashDesk cashDesk);
	void leaveQueue();
}
