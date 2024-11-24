package com.example.Restaurant;

import java.util.Queue;

import com.example.CustomerLogic.ICustomerLogic;

public class CashDesk implements ICashDesk {

	private Queue<ICustomerLogic> queue;
	private IMenu menu;
	private IOrder order;
	
	public CashDesk(Queue<ICustomerLogic> queue, IMenu menu, IOrder order) {
		this.queue = queue;
		this.menu = menu;
		this.order = order;
	}

	public Queue<ICustomerLogic> getQueue() {
		return queue;
	}

	public IOrder getOrder() {
		return order;
	}
	
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
		
	}

}
