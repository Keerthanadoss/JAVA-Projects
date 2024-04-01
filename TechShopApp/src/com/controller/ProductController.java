package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.exception.InvalidInputException;
import com.exception.ProductNotFoundException;
import com.model.Inventory;
import com.model.Product;
import com.service.ProductService;

public class ProductController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductService productService=new ProductService();
		while (true) {
			System.out.println();
			System.out.println("**********PRODUCT OPERATION**********");
			System.out.println("press 1. Get Product Details ");
			System.out.println("press 2. Update Product Info");
			System.out.println("press 3. Check whether product is in Stock");
			System.out.println("**************************************");
			int input=sc.nextInt();
			if(input==0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch(input) {
			case 1:
				try {
					List<Product> list=productService.fetchAllProduct();
					System.out.println(String.format("%-15s%-25s%-40s%s", "Product Id","Product Name", "Description","Price"));
					for(Product p:list) {
						System.out.println(String.format("%-15s%-25s%-40s%s",p.getId(),p.getProductName(),p.getDescription(),p.getPrice()));
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
				List<Product> list=productService.fetchAllProduct();
				System.out.println(String.format("%-15s%-25s%-40s%s", "Id","Name", "Description","Price"));
				for(Product p:list) {
					System.out.println(String.format("%-15s%-25s%-40s%s",p.getId(),p.getProductName(),p.getDescription(),p.getPrice()));
				}
				System.out.println("");
				System.out.println("Enter the product id to update");
				int id=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the field to update");
				String field=sc.nextLine();
				System.out.println("Enter the new Value");
				String newVal=sc.next();
				productService.updateProduct(id,field,newVal);
				System.out.println("Records updated successfully");
				} catch (SQLException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					List<Product> list=productService.fetchAllProduct();
					System.out.println(String.format("%-15s%-25s%-40s%s", "Id","Name", "Description","Price"));
					for(Product p:list) {
						System.out.println(String.format("%-15s%-25s%-40s%s",p.getId(),p.getProductName(),p.getDescription(),p.getPrice()));
					}
					System.out.println("");
					System.out.println("Enter the product to check");
					int id=sc.nextInt();
					List<Inventory> list1=productService.fetchAllInventory();
					String check=productService.checkProductAvailability(list1,id);
					System.out.println(check);
				} catch (SQLException | ProductNotFoundException e) {
					 System.out.println(e.getMessage());
				}
				break;
				
			}
		}
		sc.close();
	}
}
			

