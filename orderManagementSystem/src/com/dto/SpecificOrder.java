package com.dto;

public class SpecificOrder {
	private String userName;
	private String productName;
	@Override
	public String toString() {
		return "SpecificOrder [userName=" + userName + ", productName=" + productName + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public SpecificOrder(String userName, String productName) {
		super();
		this.userName = userName;
		this.productName = productName;
	}
	public SpecificOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
