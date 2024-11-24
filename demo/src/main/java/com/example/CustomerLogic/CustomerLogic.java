package com.example.CustomerLogic;

import java.util.List;
import java.util.Random;

import com.example.Restaurant.CashDesk;
import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IMenu;
import com.example.Restaurant.IOrder;
import com.example.Restaurant.Order;

public class CustomerLogic implements ICustomerLogic {

	private final int maxNumOfItems = 4;
	
	@Override
	public IOrder chooseOrder(IMenu menu) {
		IOrder order = new Order();
		
		Random rnd = new Random();
		int itemsToOrder = rnd.nextInt(maxNumOfItems);
		int itemsInMenu = menu.getMenuItems().size();
		
		for(int i = 0; i < itemsToOrder; ++i) {
			int itemIndex = rnd.nextInt(itemsInMenu);
			order.add(menu.chooseItem(itemIndex));
		}
		
		return order;
	}

	@Override
	public ICashDesk chooseCashDesk(List<ICashDesk> cashDesks) {
		int cds = cashDesks.size();
		
		Random rnd = new Random();
		
		return cashDesks.get(rnd.nextInt(cds));
		
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
