package com.crtlstock.services.exceptions;

public class ResourcesNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourcesNotFoundException(String message) {
		super(message);
	}


}
