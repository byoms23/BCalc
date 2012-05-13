package com.bcalc.components;

import java.io.Serializable;

public class Calc implements Serializable {
	
	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;
	private String operation; 
	private String result;
	
	public Calc(String operation, String result) {
		this.operation =  operation;
		this.result = result;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
