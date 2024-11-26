package com.example.demo.CustomerLogic;

import java.util.List;

import com.example.demo.Restaurant.CashDesk;
import com.example.demo.Restaurant.ICashDesk;
import com.example.demo.Restaurant.IMenu;
import com.example.demo.Restaurant.IOrder;

public interface ICustomerLogic {
    IOrder chooseOrder(IMenu menu);
    ICashDesk chooseCashDesk(List<ICashDesk> cashDesks);
    void leaveQueue(ICashDesk cashDesk);
}