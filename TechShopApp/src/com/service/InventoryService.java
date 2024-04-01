package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.InventoryDaoImpl;
import com.dto.QuantityOfProductInStock;
import com.dao.InventoryDao;
import com.model.Inventory;
import com.model.Product;

public class InventoryService {
	InventoryDao inventoryDao=new InventoryDaoImpl();

	public List<Inventory> fetchAllProductsDetails() throws SQLException {
		return inventoryDao.fetchAllProductsDetails();
	}

	public List<QuantityOfProductInStock> getQunatityProductsInStock() throws SQLException {
		return inventoryDao.getQunatityProductsInStock();
	}

	public void addQuantity(List<Inventory> list, int quantity, int productId, int q) throws SQLException {
	   for(Inventory i:list) {
		   if(i.getProductId()==productId) {
			   q=i.getQuantityInStock()+quantity;
			   inventoryDao.addQuantity(q,productId);
		   }
	   }
		
	}

	public void removeQuantity(List<Inventory> list, int quantity, int productId, int q) throws SQLException {
		for(Inventory i:list) {
			   if(i.getProductId()==productId & i.getQuantityInStock()!=0 & i.getQuantityInStock()>quantity) {
				   q=i.getQuantityInStock()-quantity;
				   inventoryDao.removeQuantity(q,productId);
			   }
		   }
		
	}

	public void updateQuantity(List<Inventory> list, int quantity, int productId) throws SQLException {
		for(Inventory i:list) {
			   if(i.getProductId()==productId) {
				   inventoryDao.updateQuantity(quantity,productId);
			   }
		   }
		
	}

	public void checkAvailability(List<Inventory> list, int productId) {
		for(Inventory i:list) {
			   if(i.getProductId()==productId & i.getQuantityInStock()>0){
				   System.out.println("Product is Available");
			   }	
	}
		//System.out.println("Out Of Stock");
	}

	public List<Product> fetchAllProducts() throws SQLException {
		return inventoryDao.fetchAllProducts();
	}

	
	
}
