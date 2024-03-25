package com.controller;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import com.dto.SpecificOrder;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;
import com.service.OrderManagementService;

public class OrderManagementController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		OrderManagementService order=new OrderManagementService();
		while(true) {
			System.out.println("---Menu---");
			System.out.println("Press 1. To Place Order");
			System.out.println("Press 2. To Cancel Order");
			System.out.println("Press 3. To Create Product");
			System.out.println("Press 4. To Create User");
			System.out.println("Press 5. To get all Products");
			System.out.println("Press 6. To get order by user");
			System.out.println("Press 0. To Exit");
			int input=sc.nextInt();
			if(input==0)
			{
				System.out.println("Thank You! Exiting....");
				break;
			}
			switch(input) {
			case 1:
				System.out.println("Placing Order");
				System.out.println("Enter user id");
				int userId1=sc.nextInt();
				System.out.println("Enter product id");
				int productId=sc.nextInt();
				try {
					order.createOrder(userId1,productId);
					System.out.println("Order placed successfully");
				} catch (SQLException | UserNotFoundException e2) {
					System.out.println(e2.getMessage());
				}
				break;
			case 2:
				System.out.println("Cancelling Order");
				System.out.println("Enter user id");
				int userId=sc.nextInt();
				System.out.println("Enter order id");
				int orderId=sc.nextInt();
				try {
					order.cancelOrder(userId,orderId);
					System.out.println("Order deleted successfully");
				} catch (SQLException | OrderNotFoundException | UserNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 3:
				System.out.println("Creating Product");
				System.out.println("Enter product name:");
				String productName=sc.next();
				System.out.println("Enter description");
				String desc=sc.nextLine();
				System.out.println("Enter price");
				double price=sc.nextDouble();
				System.out.println("Enter no of stocks");
				int stock=sc.nextInt();
				System.out.println("Enter type of product");
				String type=sc.next();
				Product product=new Product(productName,desc,price,stock,type);
				try {
					order.createProduct(product);
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				
				break;
			case 4:
				System.out.println("Creating User");
				System.out.println("Enter user name:");
				String name=sc.next();
				System.out.println("Enetr password:");
				String pwd=sc.next();
				System.out.println("Enetr role");
				String role=sc.next();
				User user=new User(name,pwd,role);
				try {
					order.createUser(user);
					System.out.println("user created successfully");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
				
				break;
			case 5:
				System.out.println("Retrieving Products");
				try {
				List<Product> list =order.getAllProducts();
				for(Product p: list) {
					System.out.println(p);
				}
				}catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
				break;
			case 6:
				System.out.println("Retrieving Orders by User");
				System.out.println("Enter user id:");
				int id=sc.nextInt();
				try {
					List<SpecificOrder> list=order.getOrderByUser(id);
					if(list.isEmpty()) {
						System.out.println("the user did not place order");
					}
					else {
					for(SpecificOrder o:list)
					{
						System.out.println(o);
					}
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			
			}
		}
		
		sc.close();
	}

}
