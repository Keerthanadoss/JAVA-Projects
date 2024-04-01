package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.dao.OrdersDao;
import com.dao.OrdersDaoImpl;
import com.exception.CustomerNotFoundException;
import com.exception.OrderNotFoundException;
import com.exception.ProductNotFoundException;
import com.model.Customer;
import com.model.Inventory;
import com.model.Orders;
import com.model.Product;



public class OrdersService {
   OrdersDao ordersDao=new OrdersDaoImpl();

	public List<Customer> fetchAllCustomers() throws SQLException {
		List<Customer> list = ordersDao.fetchAllCustomers();
		return list;
	}

	public Customer ValidateCustomerId(List<Customer> list, int customerId) throws CustomerNotFoundException {
		for (Customer c : list) {
			if (c.getId() == customerId)
				return c;
		}
		throw new CustomerNotFoundException("Customer not found");
	}

	public List<Product> fetchAllProducts() throws SQLException {
		List<Product> list = ordersDao.fetchAllProducts();
		return list;
	}

	public List<Inventory> fetchAllInventory() throws SQLException {
		 return ordersDao.fetchAllInventory();
	}

	public boolean checkProductAvailability(List<Inventory> list2, int productId) throws ProductNotFoundException{
		for(Inventory i:list2) {
			if(i.getProductId()==productId) 
				if(i.getQuantityInStock()>0)
					return true;
				else
					return false;
	}
		 throw new ProductNotFoundException("Product Id is not found!! Enter the correct product Id");
}

	public void insertRecordInOrder(List<Product> list1, int customerId, int productId, int quantity) throws SQLException {
		double totalAmount=0;
		 String status = null;
		 for(Product p : list1) {
				if(p.getId() == productId) {
					totalAmount=quantity*p.getPrice();
					break;
				}
				status="processed";
		 }
		 ordersDao.insertRecordInReservation(customerId,productId,LocalDate.now(),totalAmount,quantity,status);
		
	}

	public void updateProductInStock(List<Inventory> list2, int productId, int quantity) throws SQLException {
		int newStockNum=0;
		for(Inventory i: list2) {
			if(i.getProductId()==productId)
				newStockNum=(i.getQuantityInStock()-quantity);
		}
		ordersDao.updateProductInStock(newStockNum,productId);
		
	}

	public List<Orders> fetchAllOrders() throws SQLException {
		return ordersDao.fetchALLOrders();
	}

	public Orders validateOrderId(List<Orders> list, int id) throws OrderNotFoundException {
		for(Orders o:list) {
			if(o.getId()==id)
				return o;
		}
		throw new OrderNotFoundException("Order Id not Found!! Enter the correct order Id");
		
	}

	public void updateStatus(int id, String status) throws SQLException {
		ordersDao.updateStatus(id,status);
		
	}

	public void updateStock(List<Inventory> list2, int productId,int inStock,int quantity) throws SQLException {
		
		for(Inventory i:list2) {
			if(i.getProductId()==productId) {
				inStock=(i.getQuantityInStock()+quantity);
			}
		}
		ordersDao.updateStock(productId,inStock);
		
	}

	public void cancelOrder(int id) throws SQLException {
		ordersDao.cancelOrder(id);
		
	}

	public void updateQuantity(int id, int newQuantity) throws SQLException {
		ordersDao.updateQuantity(id,newQuantity);
		
	}

	public Orders getOrderById(List<Orders> list, int id) throws OrderNotFoundException {
		for(Orders o:list) {
			if(o.getId()==id) {
				return o;
			}
		}
		throw new OrderNotFoundException("Order Id not Found!! Enter the correct order Id");
	}

	
	
	
}
