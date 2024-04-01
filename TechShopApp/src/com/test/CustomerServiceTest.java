package com.test;

import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Customer;
import com.service.CustomerService;

public class CustomerServiceTest {
	
	CustomerService customerService=new CustomerService();
	Customer customer=new Customer();
	
	@Test
	public void fetchAllCustomersTest() {
		try {
			List<Customer> customerList=customerService.fetchAllCustomers();
			Assert.assertTrue(customerList.size()>0);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void getCustomerByIdTest() {
		try {
			List<Customer> list=new  ArrayList<>();
			//Use case 1:valid id
			int id=2;
			Customer expectedList=new Customer("jesse","pinkman","jesse@gmail.com","9876543210","mumbai");
			Assert.assertEquals(expectedList,customerService.getCustomerById(list, id));
			
			//use case 2:InvalidId
			id=13;
			Customer expectedList1=new Customer("jesse","pinkman","jesse@gmail.com","9876543210","mumbai");
			Assert.assertEquals(expectedList1,customerService.getCustomerById(list, id));

		} catch (InvalidInputException e) {
			Assert.assertEquals("Customer not found!!Recheck the Customer Id",e.getMessage());
		}
	}
	
	@Test
	public void createCustomerTest() {
		try {
			//List<Customer> customerList=customerService.fetchAllCustomers();
			int initialCount=customerService.fetchAllCustomers().size();
			customerService.createCustomer("keerthana","doss","mk@gmail.com", "98755434","pondy");
			int updatedCount=customerService.fetchAllCustomers().size();
			Assert.assertEquals(initialCount+1,updatedCount);
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		}
	}
	
	@Test
	public void updateCustomerTest() {
		try {
			List<Customer> list = customerService.fetchAllCustomers();
			Customer c=list.get(0);
			int id=c.getId();
			customerService.updateCustomer(id,"last name","danger");
			Customer updatedCustomer=customerService.getCustomerById(list, id);
			Assert.assertEquals("danger",updatedCustomer.getLastName());
		} catch (SQLException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Connection could not be established",e.getMessage());
		} catch (InvalidInputException e) {
			Assert.assertEquals("Customer not found!!Recheck the Customer Id",e.getMessage());
		}
		 
	}
	

}
