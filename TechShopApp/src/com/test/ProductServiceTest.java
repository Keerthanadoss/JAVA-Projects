package com.test;

import org.junit.Test;

import com.exception.InvalidInputException;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;
import com.service.ProductService;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;

public class ProductServiceTest {
	
	ProductService productService=new ProductService();
	Product product=new Product();
	Inventory inventory=new Inventory();
	
	@Test
	public void fetchAllCustomersTest() {
		try {
			//Use case 1
			List<Product> productList=productService.fetchAllProduct();
			Assert.assertTrue(productList.size()>0);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void fetchAllInventoryTest() {
		try {
			//Use case 1
			List<Inventory> inventoryList=productService.fetchAllInventory();
			Assert.assertTrue(inventoryList.size()>0);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void updateProductTest() {
		try {
			List<Product> list = productService.fetchAllProduct();
			Product p=list.get(0);
			int id=p.getId();
			productService.updateProduct(id,"name","smart phone");
			Product updatedProduct=productService.getProductById(list,id);
			Assert.assertEquals("smart phone",updatedProduct.getProductName());
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (InvalidInputException e) {
			Assert.assertEquals("Customer not found!!Recheck the Customer Id",e.getMessage());
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	


}
