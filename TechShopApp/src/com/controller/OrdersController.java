package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.exception.CustomerNotFoundException;
import com.exception.OrderNotFoundException;
import com.exception.ProductNotFoundException;
import com.model.Customer;
import com.model.Inventory;
import com.model.Orders;
import com.model.Product;
import com.service.OrdersService;

 

public class OrdersController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OrdersService ordersService=new OrdersService();
		while (true) {
			System.out.println();
			System.out.println("**********ORDER OPERATION**********");
			System.out.println("press 1. To order a product");
			System.out.println("press 2. To get order details ");
			System.out.println("press 3. To update order status ");
			System.out.println("press 4. To cancel order");
			System.out.println("press 5. To update the quantity ");
			System.out.println("press 0. for exit");
			System.out.println("**************************************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch(input) {
			case 1:
				try {
					List<Customer> list = ordersService.fetchAllCustomers();
					System.out.println("Enter your ID: ");
					int customerId = sc.nextInt();	
					sc.nextLine();
					Customer c = ordersService.ValidateCustomerId(list, customerId);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
							"First Name", "Last Name","Email", "Phone Number", "Address"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(),c.getEmail(), c.getPhone(), c.getAddress()));
					System.out.println("");
					List<Product> list1=ordersService.fetchAllProducts();
					System.out.println(String.format("%-15s%-25s%-40s%s", "Product Id","Product Name", "Description","Price"));
					for(Product p:list1) {
						System.out.println(String.format("%-15s%-25s%-40s%s",p.getId(),p.getProductName(),p.getDescription(),p.getPrice()));
					}
					System.out.println("");
					System.out.println("Enter the product id to order");
					int productId=sc.nextInt();
					sc.nextLine();
					List<Inventory> list2=ordersService.fetchAllInventory();
					boolean isInStock=ordersService.checkProductAvailability(list2,productId);
					if(isInStock == false) {
						System.out.println("Product is out of stock");
						break; 
					}
					System.out.println("Enter the quantity needed");
					int quantity=sc.nextInt();
					sc.nextLine();
					ordersService.insertRecordInOrder(list1,customerId,productId,quantity);
					ordersService.updateProductInStock(list2,productId,quantity);
					System.out.println("Order Placed Successfully!!!");
				} catch (SQLException | CustomerNotFoundException | ProductNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					List<Orders> list=ordersService.fetchAllOrders();
					System.out.println("************************************************ORDER DETAILS*********************************************");
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
							"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));
					for(Orders o:list) {
						System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
					}
					System.out.println("**********************************************************************************************************");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					List<Orders> list = ordersService.fetchAllOrders();
					System.out.println("************************************************ORDER DETAILS*********************************************");
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
							"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));
					for(Orders o:list) {
						System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
					}
					System.out.println("**********************************************************************************************************");
					System.out.println("");
					System.out.println("Enter the order id for status updation");
					int id=sc.nextInt();
					sc.nextLine();
					Orders o=ordersService.validateOrderId(list,id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
							"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));					
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
					System.out.println("Enter the new Status of the order");
					String status=sc.nextLine();
					ordersService.updateStatus(id,status);
					System.out.println("Status updated successfully");
				} catch (SQLException | OrderNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					List<Orders> list1=ordersService.fetchAllOrders();
					System.out.println("************************************************ORDER DETAILS*********************************************");
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
							"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));
					for(Orders o:list1) {
						System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
					}
					System.out.println("**********************************************************************************************************");
					System.out.println("Enter the Order Id to cancel");
					int id=sc.nextInt();
					List<Orders> list = ordersService.fetchAllOrders();
					Orders o=ordersService.validateOrderId(list,id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
							"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));					
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
					sc.nextLine();
					int productId=o.getProductId();
					List<Inventory> list2=ordersService.fetchAllInventory();
					int inStock=0;
					int quantity=o.getQuantity();
					ordersService.updateStock(list2,productId,inStock,quantity);
					ordersService.cancelOrder(id);
					System.out.println("Records deleted successfully");
				} catch (SQLException | OrderNotFoundException e) {
					 System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
				List<Orders> list = ordersService.fetchAllOrders();
				List<Inventory> list2=ordersService.fetchAllInventory();
				System.out.println("************************************************ORDER DETAILS*********************************************");
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
						"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));
				for(Orders o:list) {
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));						
				}
				System.out.println("**********************************************************************************************************");
				System.out.println("");
				System.out.println("Enter the order id for quantity updation");
				int id=sc.nextInt();
				sc.nextLine();
				Orders o=ordersService.validateOrderId(list,id);
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Order Id",
						"customer Id", "Product Id","Date", "Total Amount", "Quantity","status"));					
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s",o.getId(),o.getCustomerId(),o.getProductId(),o.getDate(),o.getTotalAmount(),o.getQuantity(),o.getStatus()));	
				int productId=o.getProductId();
				System.out.println("Enter the new quantity of the order");
				int newQuantity=sc.nextInt();
				ordersService.updateQuantity(id,newQuantity);
				ordersService.updateProductInStock(list2,productId,newQuantity);
				System.out.println("Quantity updated successfully");
				}catch (SQLException | OrderNotFoundException e) {
					 System.out.println(e.getMessage());
				}
				
				
			}
			
			
		}
sc.close();
	}

}
	
