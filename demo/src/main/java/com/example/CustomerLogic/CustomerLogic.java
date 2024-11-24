package com.example.CustomerLogic;

import com.example.Restaurant.CashDesk;
import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IMenu;
import com.example.Restaurant.IOrder;

public class CustomerLogic implements ICustomerLogic {

	@Override
	public IOrder chooseOrder(IMenu menu) {
		// TODO 
		return null;
	}

	@Override
	public ICashDesk chooseCashDesk() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void leaveQueue(ICashDesk cashDesk) {
		try {
			CashDesk cd = (CashDesk)cashDesk;	
			cd.getQueue().remove(this);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
