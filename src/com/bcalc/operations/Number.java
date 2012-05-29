package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Number extends BCalcToken {
	
	String val;
	
	public Number(String val) {
		this.val = val;
	}

	@Override
	public int getVisualHeight() {
		return BCalcToken.HEIGHT;
	}

	@Override
	public int getVisualWidth() {
		return BCalcToken.WIDTH * val.length() + PADDING_LETTERS * (val.length() - 1);
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
//	    BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, val.length());
		BCalcToken.drawRectangule(canvas, paint, padLeft, padUp, getVisualWidth(), getVisualHeight());
	    
	    canvas.drawText(val, padLeft, padUp+getVisualHeight()-5, paint);
	}

	@Override
	public double evaluate() {
		// Returns the value of this number
		Log.i("Number", val);
		return Double.parseDouble(val);
	}

}
