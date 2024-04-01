package com.test;
import org.junit.Assert;
import org.junit.Test;
import com.dto.QuantityOfProductInStock;
import com.model.Inventory;
import com.service.InventoryService;
import java.sql.SQLException;
import java.util.List;



public class InventoryServiceTest {
	InventoryService inventoryService=new InventoryService();
	Inventory inventory=new Inventory();
	
	@Test
	public void fetchAllProductTest() {
		//Use case 1:to fetch all inventory details
		try {
			List<Inventory> inventoryList=inventoryService.fetchAllProductsDetails();
			Assert.assertTrue(inventoryList.size()>0);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void listQuantityOfProduct() {
		try {
			//Use case 1:To get quantity of products in stock
			List<QuantityOfProductInStock> inventoryList=inventoryService.getQunatityProductsInStock();
			Assert.assertTrue(inventoryList.size()>0);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
 }
	


