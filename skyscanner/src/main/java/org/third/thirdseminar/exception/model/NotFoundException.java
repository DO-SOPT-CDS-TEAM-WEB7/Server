package org.third.thirdseminar.exception.model;

import org.third.thirdseminar.exception.Error;

public class NotFoundException extends CustomException {
	public NotFoundException(Error error, String message) {
		super(error, message);
	}
}
