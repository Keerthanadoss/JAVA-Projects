package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.TotalOrderByCustomer;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Customer;

public class CustomerService {
	CustomerDao customerDao = new CustomerDaoImpl();
	
	public List<Customer> fetchAllCustomers() throws SQLException, DatabaseConnectionException {
		List<Customer> list = customerDao.fetchAllCustomers();
		return list;
	}

	public Customer getCustomerById(List<Customer> list, int id) throws InvalidInputException {
		for (Customer c : list) {
			if (c.getId() == id)
				return c;
		}
		throw new InvalidInputException("Customer not found!!Recheck the Customer Id");
	}

	public void createCustomer(String firstName, String lastName, String email, String phoneNumber,String  address) throws SQLException, DatabaseConnectionException {
	 
		customerDao.createCustomer(firstName, lastName, email, phoneNumber,address);
		
	}

	public void updateCustomer(int id, String field, String newVal) throws InvalidInputException, SQLException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidInputException("Sorry!! Customer Id could not be updated");
		else
			customerDao.updateCustomer(id,fieldd,newVal);
		
	}

	public List<TotalOrderByCustomer> getTotalOrderByCustomer() throws SQLException, DatabaseConnectionException {
		return customerDao.TotalOrderByCustomer();
	}

	public TotalOrderByCustomer getTotalOrderById(List<TotalOrderByCustomer> list, int id) throws InvalidInputException {
		for (TotalOrderByCustomer dto : list){
			if (dto.getId() == id)
				return dto;
		}
		throw new InvalidInputException("Incorrect id :<");
	}

}
