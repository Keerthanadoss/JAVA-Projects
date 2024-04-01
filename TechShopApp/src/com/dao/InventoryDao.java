package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.QuantityOfProductInStock;
import com.model.Inventory;
import com.model.Product;

public interface InventoryDao {

	List<Inventory> fetchAllProductsDetails() throws SQLException;

	List<QuantityOfProductInStock> getQunatityProductsInStock() throws SQLException;

	void addQuantity(int q, int productId) throws SQLException;

	void removeQuantity(int q, int productId) throws SQLException;

	void updateQuantity(int quantity, int productId) throws SQLException;

	List<Product> fetchAllProducts() throws SQLException;

}
