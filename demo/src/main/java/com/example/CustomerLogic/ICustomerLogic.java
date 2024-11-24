package com.example.CustomerLogic;

import java.util.List;

import com.example.Restaurant.CashDesk;
import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IMenu;
import com.example.Restaurant.IOrder;

public interface ICustomerLogic {
	IOrder chooseOrder(IMenu menu);
	ICashDesk chooseCashDesk(List<ICashDesk> cashDesks);
	void leaveQueue(ICashDesk cashDesk);
}
