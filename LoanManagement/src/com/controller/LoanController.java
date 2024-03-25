package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.CustomerNotFound;
import com.exception.InvalidLoanException;
import com.model.Customer;
import com.model.Loan;
import com.service.LoanService;

public class LoanController {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		LoanService loanService=new LoanService();
		while(true) {
			System.out.println("*****************LOAN OPERATION***********************");
			System.out.println("Press 1: To Get All Loan Details");
			System.out.println("Press 2: To Get Loan by Id");
			System.out.println("Press 3: To Apply for Laon");
			System.out.println("Press 4: To check loan Status");
			System.out.println("Press 5: To calculate Interest Amount");
			System.out.println("Press 0: To exit");
			int input=sc.nextInt();
			if(input==0) {
				System.out.println("Thank you !!!Exiting !!!! ");
				break;
			}
			switch(input) {
			case 1:
				try {
					List<Loan> list=loanService.fetchAllLoanDetails();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Loan Id",
							"Customer Id", "Principal Amount", "Interest Rate", "Loan Term", "Loan Type", "Loan Status"));
					for(Loan l:list) {
						System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",l.getId(),l.getCustomerId(),l.getPrincipalAmount(),l.getInterestRate(),l.getLoanTerm(),l.getLoanType(),l.getLoanStatus()));
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				List<Loan> list;
				try {
					list = loanService.fetchAllLoanDetails();
					System.out.println("Enter the loan Id");
					int id=sc.nextInt();
					sc.nextLine();
					Loan l=loanService.getLoanById(list,id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Loan Id",
							"Customer Id", "Principal Amount", "Interest Rate", "Loan Term", "Loan Type", "Loan Status"));
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",l.getId(),l.getCustomerId(),l.getPrincipalAmount(),l.getInterestRate(),l.getLoanTerm(),l.getLoanType(),l.getLoanStatus()));
					
				} catch (SQLException | InvalidLoanException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Enter the customer id");
					int customerId=sc.nextInt();
					List<Customer> list1=loanService.fetchAllCustomer();
					loanService.validateCustomerId(list1,customerId);
					System.out.println("Enter the principal amount");
					double principalAmount=sc.nextDouble();
					sc.nextLine();
					System.out.println("Enter the Interest rate");
					int interestRate=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the loan term in months");
					int loanTerm=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the Loan Type");
					String loanType=sc.nextLine();
					System.out.println("Enter the Loan Status");
					String loanStatus=sc.nextLine();
					loanService.insertIntoLoan(customerId,principalAmount,interestRate,loanTerm,loanType,loanStatus);
					System.out.println("Loan Applied Successfully!!!");
				} catch (SQLException | CustomerNotFound e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("Enter the customer Id");
					int customerId=sc.nextInt();
					List<Customer> list1 = loanService.fetchAllCustomer();
					loanService.validateCustomerId(list1,customerId);
					boolean status=loanService.checkLoanStatus(list1,customerId);
					if(status==true)
						System.out.println("Loan Approved");
					if(status==false)
						System.out.println("Loan Rejected");
				} catch (SQLException | CustomerNotFound e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					List<Loan> list1 = loanService.fetchAllLoanDetails();
					System.out.println("Enter the loan Id to calculate Interest Amount");
					int id=sc.nextInt();
					sc.nextLine();
					Loan l=loanService.getLoanById(list,id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Loan Id",
							"Customer Id", "Principal Amount", "Interest Rate", "Loan Term", "Loan Type", "Loan Status"));
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",l.getId(),l.getCustomerId(),l.getPrincipalAmount(),l.getInterestRate(),l.getLoanTerm(),l.getLoanType(),l.getLoanStatus()));
				    double interest=loanService.calulateInterest(list1,id);
				} catch (SQLException | InvalidLoanException e) {
					System.out.println(e.getMessage());
				}
				 
		}
	}
	}
}

	


