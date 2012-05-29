package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class SingleLineOperation extends Operation {
	
	public SingleLineOperation(String op, BCalcToken term1, BCalcToken term2) {
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
		// TODO Auto-generated method stub
		term1.onDraw(canvas, paint, padLeft, padUp, cursorIndex, Cursor);

		Log.i("Prueba", (padUp + (getVisualHeight()/2) + (HEIGHT/2)) + ": " + 
				padUp + " + " + (getVisualHeight()/2) + " + " + (HEIGHT/2)); 
		
		BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, getVisualWidth(), getVisualHeight());
	    canvas.drawText(op, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS, padUp + (getVisualHeight()/2) + (HEIGHT/2) - 5, paint);
//	    canvas.drawText(op, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS, padUp, paint);

		term2.onDraw(canvas, paint, padLeft + term1.getVisualWidth() + BCalcToken.PADDING_WORDS*2 + BCalcToken.WIDTH, padUp, cursorIndex, Cursor);
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
