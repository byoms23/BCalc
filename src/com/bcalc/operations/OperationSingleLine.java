package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class OperationSingleLine extends Operation {
	
	public OperationSingleLine(String op, BCalcToken term1, BCalcToken term2) {
		super(op, term1, term2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getVisualHeight() {
		// TODO Auto-generated method stub
		int h1 = term1.getVisualHeight();
		int h2 = term2.getVisualHeight();
		if(h1 > h2) {
			return h1;
		} else {
			return h2;
		}
	}

	@Override
	public int getVisualWidth() {
		// TODO Auto-generated method stub
		return term1.getVisualWidth() + term2.getVisualWidth() + BCalcToken.PADDING_WORDS*2 + BCalcToken.WIDTH;
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		int width = getVisualWidth(), height = getVisualHeight();
		
		// Draw first term
		int tempHeight = term1.getVisualHeight();
		if(tempHeight < height) {
			term1.onDraw(canvas, paint, padLeft, padUp + (height/2) - (tempHeight/2), cursorIndex, Cursor);
		} else {
			term1.onDraw(canvas, paint, padLeft, padUp, cursorIndex, Cursor);
		}

		// Draw symbol
		BCalcToken.drawRectanguleDebug(canvas, paint, padLeft, padUp, width, height);
	    canvas.drawText(op, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS, padUp + (getVisualHeight()/2) + (HEIGHT/2) - 5, paint);
//	    canvas.drawText(op, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS, padUp, paint);

	    // Draw second term
		tempHeight = term2.getVisualHeight();
		if(tempHeight < height) {
//			term1.onDraw(canvas, paint, padLeft, padUp + (height/2) - (tempHeight/2), cursorIndex, Cursor);
			term2.onDraw(canvas, paint, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS*2 + BCalcToken.WIDTH, padUp + (height/2) - (tempHeight/2), cursorIndex, Cursor);
		} else {
			term2.onDraw(canvas, paint, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS*2 + BCalcToken.WIDTH, padUp, cursorIndex, Cursor);
		}
		
	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		if(op.equals("+")) {
			return term1.evaluate() + term2.evaluate();
		} else if (op.equals("-")) {
			return term1.evaluate() - term2.evaluate();
		} else if (op.equals("*")) {
			return term1.evaluate() * term2.evaluate();
		} else if (op.equals("%")) {
			return term1.evaluate() % term2.evaluate();
		}
		return 0;
	}


}
