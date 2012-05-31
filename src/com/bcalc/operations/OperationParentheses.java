package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class OperationParentheses extends Operation {

	public OperationParentheses(String op, BCalcToken term1) {
		super(op, term1, null);
	}

	@Override
	public int getVisualHeight() {
		int height = term1.getVisualHeight();
		
		return height;
	}

	@Override
	public int getVisualWidth() {
		int width = 0;
		int height = getVisualHeight();
		
		width += term1.getVisualWidth();
		if(op.equals("(")) {
			width += PADDING_WORDS;
			width += height/2;
		} else {
			width += PADDING_WORDS * 2;
			width += height;
		}
		
		return width;
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// Draw division
		int height = getVisualHeight();
		int width = getVisualWidth();
		int parentheses_width = 0;
		
		// Calculate size of the parentheses
		if(height/2 % 2 == 0) {
			parentheses_width = height/2;
		} else {
			parentheses_width = (height+1)/2;
		}

		// Draw the openning parentheses
//		Log.w("Parenthesis", height + "");
//		Log.w("Parenthesis", (padUp+height) + "");
		paint.setTextSize(height);
		canvas.drawText("(", padLeft, padUp+height-5*((height+.1f-.1f)/HEIGHT), paint);
		paint.setTextSize(SIZE);
//		BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, parentheses_width, padUp+height);
//		BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, getVisualWidth(), getVisualHeight());
		
		// Draw the text
		term1.onDraw(canvas, paint, padLeft+BCalcToken.PADDING_WORDS+parentheses_width, padUp, cursorIndex, Cursor);
	
		// Draw the openning parentheses
		if(op.endsWith(")")) {
			paint.setTextSize(height-2);
			canvas.drawText(")", padLeft+2*BCalcToken.PADDING_WORDS+parentheses_width+term1.getVisualWidth(), padUp+height-5*((height+.1f-.1f)/HEIGHT), paint);
			paint.setTextSize(SIZE);
		}
	}

	@Override
	public double evaluate() {
		return term1.evaluate();
	}

}
