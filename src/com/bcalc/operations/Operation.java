package com.bcalc.operations;


public abstract class Operation implements BCalcToken {

	protected BCalcToken term1, term2;
	protected String op; 
	
	public Operation(String op, BCalcToken term1, BCalcToken term2) {
		this.op = op;
		this.term1 = term1;
		this.term2 = term2;
	}


	public BCalcToken getTerm1() {
		return term1;
	}

	public void setTerm1(BCalcToken term1) {
		this.term1 = term1;
	}

	public BCalcToken getTerm2() {
		return term2;
	}

	public void setTerm2(BCalcToken term2) {
		this.term2 = term2;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
