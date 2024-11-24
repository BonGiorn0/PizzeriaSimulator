package com.example.Customers;

import com.example.CustomerLogic.*;
import com.example.GraphicalView.*;
import com.example.Restaurant.*;

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
