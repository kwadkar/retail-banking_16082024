package com.academy.miniproject.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
