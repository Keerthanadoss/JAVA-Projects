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

	public double calulateInterest(List<Loan> list1, int id,int loanTerm) {
		double amount=0;
		for(Loan l:list1) {
			if(l.getId()==id) {
				loanTerm=(l.getLoanTerm())/12;
				amount=(l.getPrincipalAmount()*l.getInterestRate()*loanTerm)/100;
				
			}
		}
		return amount;
	}

	public double calculateEMI(List<Loan> list1, int id, double interest,int r) throws InvalidLoanException {
		 for(Loan l:list1) {
			 r=l.getInterestRate()/12/100;
				if(l.getId()==id) {
					double i=Math.pow((1+r),l.getLoanTerm());
					double j=Math.pow((1+r),l.getLoanTerm()-1);
					 return ((l.getPrincipalAmount()*r*i))/j;
				}
				}
		 throw new InvalidLoanException("Loan Id Not Found!!Please enter the correct Loan Id");
	}

	public double calculateNoOfMonths(List<Loan> list1, double emi,int id) {
		double month=0;
		for(Loan l:list1) {
			if(l.getId()==id) {
				month=l.getPrincipalAmount()/emi;
			}		
	}
		return month;
	}

}
