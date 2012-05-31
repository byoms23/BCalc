package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class OperationDivision extends Operation {

	public OperationDivision(String op, BCalcToken term1, BCalcToken term2) {
		super(op, term1, term2);
	}

	@Override
	public int getVisualHeight() {
		int height = 0;
//		if (op.equals("/")) {
			height += term1.getVisualHeight();
			if(term2 != null) { 
				height += term2.getVisualHeight(); 
			} else {
				height += HEIGHT;
			}
			height += 1;
			
//		} else if (op.equals("^")) {
//			return term1.getVisualHeight() + (term2.getVisualHeight() / 2);
//		} else {
//			height = HEIGHT;
//		}
		
		return height;
	}

	@Override
	public int getVisualWidth() {
		int width = 0;
		
		int w1 = term1.getVisualWidth();
		if(term2 != null) {
			int w2 = term2.getVisualWidth();
			if(w1 > w2) {
				width = w1;
			} else {
				width = w2;
			}
		} else {
			width = w1; 
		}
		
		width += PADDING_WORDS * 2;
		
		return width;
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// Draw division
		if (op.equals("/")) {
			BCalcToken.drawRectanguleDebug(canvas, paint, padLeft, padUp, getVisualWidth(), getVisualHeight());

			int width = getVisualWidth() - 2*BCalcToken.PADDING_WORDS;
			int tempWidth = term1.getVisualWidth();
			if(tempWidth < width) {
				term1.onDraw(canvas, paint, padLeft+BCalcToken.PADDING_WORDS+((width-tempWidth)/2), padUp, cursorIndex, Cursor);
			} else {
				term1.onDraw(canvas, paint, padLeft+BCalcToken.PADDING_WORDS, padUp, cursorIndex, Cursor);
			}
	
		    canvas.drawLine(padLeft, padUp + term1.getVisualHeight(), padLeft + getVisualWidth(), padUp + term1.getVisualHeight(), paint);
		    
		    if(term2 != null) {
				tempWidth = term2.getVisualWidth();
				if(tempWidth < width) {
					term2.onDraw(canvas, paint, padLeft+BCalcToken.PADDING_WORDS+((width-tempWidth)/2), padUp + term1.getVisualHeight() + 1, cursorIndex, Cursor);
				} else {
					term2.onDraw(canvas, paint, padLeft+BCalcToken.PADDING_WORDS, padUp + term1.getVisualHeight() + 1, cursorIndex, Cursor);
				}
		    } else {
		    	BCalcToken.drawRectanguleDebug(canvas, paint, padLeft + BCalcToken.PADDING_WORDS, padUp + term1.getVisualHeight() + 2, WIDTH, HEIGHT);
		    }
		}
	}

	@Override
	public double evaluate() {
		if (op.equals("/")) {
			return term1.evaluate() / term2.evaluate();
		} else if (op.equals("^")) {
			return term1.evaluate() / term2.evaluate();
		}
			
		return 0;
	}
	
}
