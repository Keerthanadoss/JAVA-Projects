package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Inventory;
import com.model.Product;

public interface ProductDao {

	List<Product> fetchAllProduct() throws SQLException;

	void updateProduct(int id, String field1, String newVal) throws SQLException;

	List<Inventory> fetchAllInventory() throws SQLException;

}
