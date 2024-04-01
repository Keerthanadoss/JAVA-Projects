package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.ProductDao;
import com.dao.ProductDaoImpl;
import com.exception.InvalidInputException;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;


public class ProductService {
	ProductDao productDao=new ProductDaoImpl();

	public List<Product> fetchAllProduct() throws SQLException {
		return productDao.fetchAllProduct();
		
	}

	public void updateProduct(int id, String field, String newVal) throws InvalidInputException, SQLException {
		String field1=field.toLowerCase().replace(" ","_");
		if(field1.equals("id"))
			throw new InvalidInputException("Sorry!! Product Id could not be updated :<");
		else
			productDao.updateProduct(id,field1,newVal);
		
		
	}

	public List<Inventory> fetchAllInventory() throws SQLException {
		return productDao.fetchAllInventory();
	}

	public String checkProductAvailability(List<Inventory> list, int id) throws ProductNotFoundException {
		for(Inventory i:list) {
			if(i.getProductId()==id) 
				if(i.getQuantityInStock()>0)
					return "Product is in stock";
				else
					return "Product is out of Stock";			
		}
	  throw new ProductNotFoundException("Product Id is not found!! Enter the correct product Id");
	}

	public Product getProductById(List<Product> list, int id) throws ProductNotFoundException {
		for(Product p:list) {
			if(p.getId()==id)
				return p;
		}
		throw new ProductNotFoundException("Product Id is not found!! Enter the correct product Id");
	}
 
	

}
