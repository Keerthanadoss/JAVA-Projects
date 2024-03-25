package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Customer;
import com.model.Loan;

public interface LoanDao {

	List<Loan> fetchAllLoanDetails() throws SQLException;

	List<Customer> fetchAllCustomer() throws SQLException;

	void insertIntoLoan(int customerId, double principalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) throws SQLException;

}
