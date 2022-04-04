package com.project.todoInfom.exceptions;

public class IntegrityViolationOnlyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IntegrityViolationOnlyException(String message) {
		super(message);

	}
}
