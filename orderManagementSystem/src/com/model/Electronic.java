package com.model;

public class Electronic extends Product{
	private String brand;
	private int warrantyPeriod;
	public Electronic() {
		
	}
	@Override
	public String toString() {
		return "Electronic [brand=" + brand + ", warrantyPeriod=" + warrantyPeriod + "]";
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	public Electronic(int productId, String productName, String description, double price, int quantityInStock,
			String type, String brand, int warrantyPeriod, String brand2, int warrantyPeriod2) {
		
		brand = brand2;
		warrantyPeriod = warrantyPeriod2;
	}

}
