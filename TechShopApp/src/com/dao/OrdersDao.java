package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.model.Customer;
import com.model.Inventory;
import com.model.Orders;
import com.model.Product;

public interface OrdersDao {

	List<Customer> fetchAllCustomers() throws SQLException;

	List<Product> fetchAllProducts() throws SQLException;

	List<Inventory> fetchAllInventory() throws SQLException;

	void insertRecordInReservation(int customerId, int productId, LocalDate now, double totalAmount, int quantity,
			String status) throws SQLException;

	void updateProductInStock(int newStockNum, int productId) throws SQLException;

	List<Orders> fetchALLOrders() throws SQLException;

	void updateStatus(int id, String status) throws SQLException;

	void updateStock(int productId, int inStock) throws SQLException;

	void cancelOrder(int id) throws SQLException;

	void updateQuantity(int id, int newQuantity) throws SQLException;

	 
}
