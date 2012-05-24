package com.bcalc.operations;

import android.graphics.Canvas;

public class MultipleLineOperation extends Operation {

	private boolean isSingleTerm = false;
	
	public MultipleLineOperation(String op, BCalcToken term1, BCalcToken term2) {
		super(op, term1, term2);
		// TODO Auto-generated constructor stub
	}

	public MultipleLineOperation(String op, BCalcToken term1) {
		super(op, term1, null);
		this.setSingleTerm(true);
	}

	@Override
	public int getVisualHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVisualWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onDraw(Canvas canvas, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isSingleTerm() {
		return isSingleTerm;
	}

	public void setSingleTerm(boolean isSingleTerm) {
		this.isSingleTerm = isSingleTerm;
	}

}
