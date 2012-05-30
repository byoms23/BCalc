package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Paint;

public class EmptyToken extends BCalcToken {

	@Override
	public int getVisualHeight() {
		return HEIGHT;
	}

	@Override
	public int getVisualWidth() {
		return WIDTH;
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp,
			int cursorIndex, boolean Cursor) {
		BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, getVisualWidth(), getVisualHeight());
	}

	@Override
	public double evaluate() {
		throw new java.lang.ArithmeticException();
	}

}
