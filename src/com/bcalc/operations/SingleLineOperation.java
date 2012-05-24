package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class SingleLineOperation extends Operation {
	
	public SingleLineOperation(String op, BCalcToken term1, BCalcToken term2) {
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
		return term1.getVisualWidth()+term2.getVisualWidth()+BCalcToken.PADDING*2+BCalcToken.WIDTH;
	}

	@Override
	public void onDraw(Canvas canvas, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// TODO Auto-generated method stub
		term1.onDraw(canvas, padLeft, padUp, cursorIndex, Cursor);

		Paint paint = new Paint();
	    paint.setStyle(Style.FILL_AND_STROKE);
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    paint.setTextSize(20);
	    canvas.drawText(op, padLeft + term1.getVisualWidth() + BCalcToken.PADDING, padUp, paint);

		term2.onDraw(canvas, padLeft+term1.getVisualWidth()+BCalcToken.PADDING*2+BCalcToken.WIDTH, padUp, cursorIndex, Cursor);
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
