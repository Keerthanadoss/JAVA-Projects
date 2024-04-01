package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.TotalOrderByCustomer;
import com.exception.DatabaseConnectionException;
import com.model.Customer;

public interface CustomerDao {

	List<Customer> fetchAllCustomers() throws SQLException, DatabaseConnectionException;

	void createCustomer(String firstName, String lastName, String email, String phoneNumber,String  address) throws SQLException, DatabaseConnectionException;

	void updateCustomer(int id, String fieldd, String newVal) throws SQLException;

	List<TotalOrderByCustomer> TotalOrderByCustomer() throws SQLException, DatabaseConnectionException;

}
