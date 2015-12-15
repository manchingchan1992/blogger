package com.blogger.app.authentication;

import org.springframework.security.core.AuthenticationException;

public class inputValidationException extends AuthenticationException {
	public inputValidationException(String msg) {
		super(msg);
	}
}
