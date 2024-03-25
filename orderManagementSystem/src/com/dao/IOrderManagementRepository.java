package com.dao;

import java.sql.SQLException;

import java.util.List;

import com.dto.SpecificOrder;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;

public interface IOrderManagementRepository {
	public void createUser(User user) throws SQLException;
	public List<Product> getAllProducts() throws SQLException;
	public List<SpecificOrder> getOrderByUser(int id) throws SQLException;
	public void cancelOrder(int userId, int orderId) throws SQLException, OrderNotFoundException, UserNotFoundException;
	public void createProduct(Product product) throws SQLException;
	public void createOrder(int userId1, int productId) throws SQLException, UserNotFoundException;

}
