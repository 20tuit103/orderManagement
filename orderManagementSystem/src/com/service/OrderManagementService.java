package com.service;

import java.sql.SQLException;

import java.util.List;

import com.dao.OrderProcessor;
import com.dto.SpecificOrder;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;

public class OrderManagementService {
	OrderProcessor pro=new OrderProcessor();

	public void createUser(User user) throws SQLException {
		pro.createUser(user);
		
	}

	public List<Product> getAllProducts() throws SQLException {
		
		return pro.getAllProducts();
	}

	public List<SpecificOrder> getOrderByUser(int id) throws SQLException {
		
		return pro.getOrderByUser(id);
	}

	public void cancelOrder(int userId, int orderId) throws SQLException, OrderNotFoundException, UserNotFoundException {
		pro.cancelOrder(userId,orderId);
		
	}

	public void createProduct(Product product) throws SQLException {
		pro.createProduct(product);
		
	}

	public void createOrder(int userId1, int productId) throws SQLException, UserNotFoundException {
		pro.createOrder(userId1,productId);
		
	}

}
