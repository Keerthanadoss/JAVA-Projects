package com.test;


import com.exception.OrderNotFoundException;
import com.model.Orders;
import com.model.Product;
import com.service.OrdersService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;

public class OrdersServiceTest {
	
	OrdersService orderService=new OrdersService();
	Orders orders=new Orders();
	
	@Test
	public void createOrderTest() {
		try {
			List<Product> list1=orderService.fetchAllProducts();
			int initialCount=orderService.fetchAllOrders().size();
			orderService.insertRecordInOrder(list1,4,3,2);
			int updatedCount=orderService.fetchAllOrders().size();
			Assert.assertEquals(initialCount+1,updatedCount);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void updateStatusTest() {
		try {
			List<Orders> list = orderService.fetchAllOrders();
			Orders o=list.get(0);
			int id=o.getId();
			orderService.updateStatus(id,"pending");
			Orders updatedOrder=orderService.getOrderById(list,id);
			Assert.assertEquals("pending",updatedOrder.getStatus());
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (OrderNotFoundException e) {
			Assert.assertEquals("Order Id not Found!! Enter the correct order Id",e.getMessage());
		}
		 
	}
	
	@Test
	public void updateQuantityTest() {
		try {
			List<Orders> list = orderService.fetchAllOrders();
			Orders o=list.get(0);
			int id=o.getId();
			orderService.updateQuantity(id,4);
			Orders updatedOrder=orderService.getOrderById(list,id);
			Assert.assertEquals(4,updatedOrder.getQuantity());
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (OrderNotFoundException e) {
			Assert.assertEquals("Order Id not Found!! Enter the correct order Id",e.getMessage());
		}
		 
	}

}
