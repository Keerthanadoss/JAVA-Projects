package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.LoanDao;
import com.dao.LoanDaoImpl;
import com.exception.CustomerNotFound;
import com.exception.InvalidLoanException;
import com.model.Customer;
import com.model.Loan;

public class LoanService {
	LoanDao loanDao=new LoanDaoImpl();

	public List<Loan> fetchAllLoanDetails() throws SQLException {
		return loanDao.fetchAllLoanDetails();
	}

	public Loan getLoanById(List<Loan> list, int id) throws InvalidLoanException {
		for(Loan l:list) {
			if(l.getId()==id)
				return l;
		}
		throw new InvalidLoanException("Loan Id Not Found!!Please enter the correct Loan Id");
	}

	public List<Customer> fetchAllCustomer() throws SQLException {
		return loanDao.fetchAllCustomer();
	}

	public Customer validateCustomerId(List<Customer> list1, int customerId) throws CustomerNotFound {
		for(Customer c:list1) {
			if(c.getId()==customerId)
				return c;
		}
		throw new CustomerNotFound("Invalid Id!!Customer Not found");
		
	}

	public void insertIntoLoan(int customerId, double principalAmount, int interestRate, int loanTerm, String loanType,
			String loanStatus) throws SQLException {
		loanDao.insertIntoLoan(customerId,principalAmount,interestRate,loanTerm,loanType,loanStatus);
		
	}

	public boolean checkLoanStatus(List<Customer> list1, int customerId) throws CustomerNotFound {
		for(Customer c:list1) {
			if(c.getId()==customerId) {
			if(c.getCreditScore()>650)
				return true;
			else
				return false;
		}
		}
		throw new CustomerNotFound("Invalid Id!!Customer Not found");
		
	}

}
