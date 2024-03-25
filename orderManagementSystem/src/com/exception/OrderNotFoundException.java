package com.exception;

public class OrderNotFoundException extends Exception{
	
	
	private static final long serialVersionUID = 1L;
	private String message;
	public OrderNotFoundException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

}


