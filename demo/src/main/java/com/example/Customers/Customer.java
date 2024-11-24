package com.example.Customers;

import com.example.CustomerLogic.*;
import com.example.GraphicalView.*;
import com.example.Restaurant.*;

public class Customer{
	private IPhysicalObject physicalObject;
	private ICustomerLogic customerLogic;
	private ICashDesk cashDesk;
	private IOrder order;
	
	public Customer(IPhysicalObject physicalObject,
			ICustomerLogic customerLogic)
	{
		this.physicalObject = physicalObject;
		this.customerLogic = customerLogic;
	}
	
//	public void enter(IMenu menu) {
//		this.cashDesk = customerLogic.chooseCashDesk();
//		this.order = customerLogic.chooseOrder(menu);
//	}
	
	public IPhysicalObject getPhysicalObject() {
		return physicalObject;
	}
	
	public ICustomerLogic getCustomerLogic() {
		return customerLogic;
	}
	
	public ICashDesk getCashDesk() {
		return cashDesk;
	}
	
	public void setCashDesk(ICashDesk cashDesk) {
		this.cashDesk = cashDesk;
		cashDesk.addCustomerToQueue(customerLogic);
	}

	public void setOrder(IOrder order) {
		this.order = order;		
	}
	
}
