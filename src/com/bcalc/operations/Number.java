package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Number implements BCalcToken {
	
	String val;
	
	public Number(String val) {
		this.val = val;
	}

	@Override
	public int getVisualHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVisualWidth() {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public void onDraw(Canvas canvas, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
	    paint.setStyle(Style.FILL_AND_STROKE);
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    paint.setTextSize(20);
	    canvas.drawText(val, padLeft, padUp, paint);

	}

	@Override
	public double evaluate() {
		// Returns the value of this number
		Log.i("Number", val);
		return Double.parseDouble(val);
	}

}
