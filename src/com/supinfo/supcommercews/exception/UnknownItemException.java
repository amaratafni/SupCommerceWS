package com.supinfo.supcommercews.exception;

public class UnknownItemException extends RuntimeException {

	private static final long serialVersionUID = 2759170024413623061L;
	
	public UnknownItemException(Object item) {
		super("Item doesn't exist "+item.toString());
	}
	
}
