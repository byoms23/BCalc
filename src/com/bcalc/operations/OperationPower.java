package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Paint;

public class OperationPower extends Operation {

	public OperationPower(String op, BCalcToken term1, BCalcToken term2) {
		super(op, term1, term2);
		// TODO Auto-generated constructor stub
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
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp,
			int cursorIndex, boolean Cursor) {
		// TODO Auto-generated method stub

	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
