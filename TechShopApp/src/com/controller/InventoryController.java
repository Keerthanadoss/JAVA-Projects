package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.model.Product;
import com.dto.QuantityOfProductInStock;
import com.model.Inventory;
import com.service.InventoryService;
 

public class InventoryController {

	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			InventoryService inventoryService=new InventoryService();
			while (true) {
				System.out.println();
				System.out.println("**********INVENTORY OPERATION**********");
				System.out.println("press 1. Get All Products");
				System.out.println("press 2. Get Quantity in Stock ");
				System.out.println("press 3. To add to quantity");
				System.out.println("press 4. To decrease from quantity in Stock ");
				System.out.println("press 5. To update Stock Quantity  ");
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
						List<Inventory> list=inventoryService.fetchAllProductsDetails();
						System.out.println(String.format("%-15s%-15s%-15s%s", "Inventory Id",
								"Quantity In Stock", "Last Stock Update","Product Id"));
						for(Inventory i:list) {
							System.out.println(String.format("%-15s%-15s%-15s%s",i.getId(),i.getQuantityInStock(),i.getLastStockUpdate(),i.getProductId()));
						}			
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					try {
						List<QuantityOfProductInStock> list1=inventoryService.getQunatityProductsInStock();
						System.out.println(String.format("%-15s%-15s%s", "Product Id","Product name",
								"Quantity In Stock"));
						for(QuantityOfProductInStock q:list1) {
							System.out.println(String.format("%-15s%-15s%s",q.getProductId(),q.getProductName(),q.getQuantityInStock()));
						}		
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						List<Inventory> list = inventoryService.fetchAllProductsDetails();
						System.out.println(String.format("%-15s%-15s%-15s%s", "Inventory Id",
								"Quantity In Stock", "Last Stock Update","Product Id"));
						for(Inventory i:list) {
							System.out.println(String.format("%-15s%-15s%-15s%s",i.getId(),i.getQuantityInStock(),i.getLastStockUpdate(),i.getProductId()));
						}	
						System.out.println("");
						System.out.println("Enter the product id to add the quantity");
						int productId=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the quantity to add");
						int quantity=sc.nextInt();
						sc.nextLine();
						int q=0;
						inventoryService.addQuantity(list,quantity,productId,q);
						System.out.println("Quantity added successfully");
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4:
					try {
						List<Inventory> list = inventoryService.fetchAllProductsDetails();
						System.out.println(String.format("%-15s%-15s%-15s%s", "Inventory Id",
								"Quantity In Stock", "Last Stock Update","Product Id"));
						for(Inventory i:list) {
							System.out.println(String.format("%-15s%-15s%-15s%s",i.getId(),i.getQuantityInStock(),i.getLastStockUpdate(),i.getProductId()));
						}	
						System.out.println("");
						System.out.println("Enter the product id to remove the quantity");
						int productId=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the quantity to remove");
						int quantity=sc.nextInt();
						sc.nextLine();
						int q=0;
						inventoryService.removeQuantity(list,quantity,productId,q);
						System.out.println("Quantity removed successfully");
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					try {
						List<Inventory> list = inventoryService.fetchAllProductsDetails();
						System.out.println(String.format("%-15s%-15s%-15s%s", "Inventory Id",
								"Quantity In Stock", "Last Stock Update","Product Id"));
						for(Inventory i:list) {
							System.out.println(String.format("%-15s%-15s%-15s%s",i.getId(),i.getQuantityInStock(),i.getLastStockUpdate(),i.getProductId()));
						}	
						System.out.println("");
						System.out.println("Enter the product id to update the quantity");
						int productId=sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the new quantity");
						int quantity=sc.nextInt();
						sc.nextLine();
						inventoryService.updateQuantity(list,quantity,productId);
						System.out.println("Quantity updated successfully");
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					try {
						List<Inventory> list=inventoryService.fetchAllProductsDetails();
						List<Product> list1=inventoryService.fetchAllProducts();
						System.out.println(String.format("%-15s%-15s%-15s%s", "Id",
								"Name", "Description","Price"));
						for(Product p:list1) {
							System.out.println(String.format("%-15s%-15s%-15s%s",p.getId(),p.getProductName(),p.getDescription(),p.getPrice()));
						}
						System.out.println("");
						System.out.println("Enter the product id to check status");
						int productId=sc.nextInt();
						sc.nextLine();
						inventoryService.checkAvailability(list,productId);		
					
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
					

	
				}
			}
			sc.close();
	}
}
