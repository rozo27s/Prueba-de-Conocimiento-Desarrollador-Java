package com.service.contain.utils;

import org.springframework.validation.BindingResult;

public class DataException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final transient BindingResult result;
	
	public DataException(BindingResult result) {
		super();
		this.result=result;		
	}
	
	public DataException (String mensaje,BindingResult result) {
		super(mensaje);
		this.result= result;
	}
	
	public BindingResult getResult() {
		return result;
	}
	

}
