package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.dto.TotalOrderByCustomer;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Customer;
import com.service.CustomerService;

public class CustomerController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		while (true) {
			System.out.println();
			System.out.println("**********CUSTOMER OPERATION**********");
			System.out.println("press 1. To Calculate Total Order by customer ");
			System.out.println("press 2. Get Customer Details by Id");
			System.out.println("press 3. Create Customer Info");
			System.out.println("press 4. Update Customer Info");
			System.out.println("press 0. for exit");
			System.out.println("**************************************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch(input){
			case 1:
				try {
					System.out.println("Enter the customer ID");
					int id=sc.nextInt();
					sc.nextLine();
					List<TotalOrderByCustomer> list = customerService.getTotalOrderByCustomer();
					TotalOrderByCustomer t=customerService.getTotalOrderById(list,id);
					System.out.println(String.format("%-15s%-15s%s", "Customer Id","First Name", "Total Order"));
					System.out.println(String.format("%-15s%-15s%s",t.getId(),t.getFirstName(),t.getCount()));
					
				}catch(SQLException | DatabaseConnectionException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				  try {
					List<Customer> list = customerService.fetchAllCustomers();
					System.out.println("Enter Customer Id:");
					int id = sc.nextInt();
					Customer c = customerService.getCustomerById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
							"First Name", "Last Name","Email", "Phone Number", "Address"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(),c.getEmail(), c.getPhone(), c.getAddress()));
				}catch(DatabaseConnectionException | SQLException | InvalidInputException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				System.out.println("Enter First Name:");
				String firstName=sc.next();
				System.out.println("Enter Last Name:");
				String lastName=sc.next();
				System.out.println("Enter Email Id:");
				String email=sc.next();
				System.out.println("Enter Phone Number:");
				String phoneNumber=sc.next();
				System.out.println("Enter Address:");
				String address=sc.next();
				try {
					customerService.createCustomer(firstName,lastName,email,phoneNumber,address);
					System.out.println("Registration Successfull!!");
				} catch (DatabaseConnectionException |SQLException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 4:
				try {
					List<Customer>list = customerService.fetchAllCustomers();
					System.out.println("Enter Customer Id to be updated:");
					int id = sc.nextInt();
					System.out.println();
					Customer c = customerService.getCustomerById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
							"First Name", "Last Name","Email", "Phone", "Address"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(),c.getEmail(), c.getPhone(), c.getAddress()));
					System.out.println("What do you want to update?");
					sc.nextLine();				
					String field=sc.nextLine();
					System.out.println("Enter the new value:");
					String newVal=sc.next();
					customerService.updateCustomer(id,field,newVal);
					System.out.println("Record updated Successfully");
				} catch (SQLException | DatabaseConnectionException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
		     
		}
	}
		sc.close();
}
}
			
