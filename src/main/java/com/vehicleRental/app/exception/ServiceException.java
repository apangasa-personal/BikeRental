package com.vehicleRental.app.exception;

public class ServiceException extends RuntimeException {

	public ServiceException(String message) {
		super(message);

	}

	public ServiceException() {
		super();
	}

}
