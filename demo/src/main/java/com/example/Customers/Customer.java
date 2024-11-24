package com.example.Customers;

import com.example.Customers.CustomerLogic.ICustomerLogic;
import com.example.Restaurant.ICashDesk;
import com.example.Restaurant.IOrder;
import com.example.Restaurant.GraphicalView.IMovingPerson;
import com.example.Restaurant.GraphicalView.IPhysicalObject;

public class Customer{
	private IPhysicalObject physicalObject;
	private IMovingPerson movingPerson;
	private ICustomerLogic customerLogic;
	private ICashDesk cashDesk;
	private IOrder order;
	
	
	public IPhysicalObject getPhysicalObject() {
		return physicalObject;
	}
	public IMovingPerson getMovingPerson() {
		return movingPerson;
	}
	public ICustomerLogic getCustomerLogic() {
		return customerLogic;
	}
	public ICashDesk getCashDesk() {
		return cashDesk;
	}
	public IOrder getOrder() {
		return order;
	}
	
	
}
