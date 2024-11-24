package com.example.CustomerLogic;

import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IMenu;
import com.example.Restaurant.IOrder;

public interface ICustomerLogic {
	IOrder chooseOrder(IMenu menu);
	ICashDesk chooseCashDesk();
	void leaveQueue(ICashDesk cashDesk);
}
