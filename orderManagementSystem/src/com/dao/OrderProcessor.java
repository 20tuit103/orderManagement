package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.dto.SpecificOrder;
import com.exception.OrderNotFoundException;
import com.exception.UserNotFoundException;
import com.model.Product;
import com.model.User;
import com.util.DBUtil;

public class OrderProcessor implements IOrderManagementRepository{

	public void createUser(User user) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql="insert into user(user_name,password,role) values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		String name=user.getUserName();
		String password=user.getPassword();
		String role=user.getRole();
		pstmt.setString(1,name );
		pstmt.setString(2,password);
		pstmt.setString(3,role);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	public List<Product> getAllProducts() throws SQLException {
		List<Product> list=new ArrayList<>();
		Connection conn=DBUtil.getDBConn();
		String sql="select * from product";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rst=pstmt.executeQuery();
		
		while(rst.next()) {
			int id=rst.getInt("product_id");
			String name=rst.getString("product_name");
			String description=rst.getString("description");
			double price=rst.getDouble("price");
			int stock=rst.getInt("quantity_in_stock");
			String type=rst.getString("type");
			
			Product product=new Product(id,name,description,price,stock,type);
			list.add(product);
			}
		DBUtil.dbClose();
		return list;
	}

	public List<SpecificOrder> getOrderByUser(int id) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		List<SpecificOrder> list=new ArrayList<>();
		String sql="select u.user_name,p.product_name from user u,order_placed o,product p\r\n"
				+ "where u.user_id=o.user_user_id and p.product_id=o.product_product_id and u.user_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rst=pstmt.executeQuery();
		
		while(rst.next())
		{
			SpecificOrder order=new SpecificOrder();
			String userName=rst.getString("user_name");
			String productName=rst.getString("product_name");
			order.setUserName(userName);
			order.setProductName(productName);
			list.add(order);
		}
		
		DBUtil.dbClose();
		return list;
	}

	public void cancelOrder(int userId, int orderId) throws SQLException, OrderNotFoundException, UserNotFoundException {
        Connection conn = DBUtil.getDBConn();
		
		
		String query="select user_id from user where user_id=?";
		PreparedStatement pstmt1 = conn.prepareStatement(query);
		pstmt1.setInt(1,userId);
		ResultSet idSet= pstmt1.executeQuery();	
		
		if(!(idSet.next()))
			throw new UserNotFoundException("invalid user id");
		String query1="select order_id from order_placed where order_id=?";
		PreparedStatement pstmt2 = conn.prepareStatement(query1);
		pstmt2.setInt(1,orderId);
		ResultSet idSet1= pstmt2.executeQuery();	
        
       
        if(!(idSet1.next()))
			throw new OrderNotFoundException("invalid order id");
 	
        String sql="delete from order_placed where order_id=? and user_user_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		pstmt.setInt(2,userId );
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	public void createProduct(Product product) throws SQLException {
		Connection conn = DBUtil.getDBConn();
		String sql="insert into product(product_name,description,price,quantity_in_stock,type) values (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		String name=product.getProductName();
		String desc=product.getDescription();
		double price=product.getPrice();
		int stock=product.getQuantityInStock();
		String type=product.getType();
		pstmt.setString(1,name);
		pstmt.setString(2,desc);
		pstmt.setDouble(3, price);
		pstmt.setInt(4, stock);
		pstmt.setString(5,type);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}

	public void createOrder(int userId1, int productId) throws SQLException, UserNotFoundException {
		Connection conn = DBUtil.getDBConn();
		String query="select user_id from user where user_id=?";
		PreparedStatement pstmt1 = conn.prepareStatement(query);
		pstmt1.setInt(1,userId1);
		ResultSet idSet= pstmt1.executeQuery();
		if(!(idSet.next()))
			throw new UserNotFoundException("invalid user id");
		String sql="insert into order_placed(product_product_id,user_user_id) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,productId);
		pstmt.setInt(2,userId1);
		pstmt.executeUpdate();
		DBUtil.dbClose();
		
	}
	

}
