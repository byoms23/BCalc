package com.bcalc.widget;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.bcalc.operations.BCalcToken;
import com.bcalc.parser.Parser;
import com.bcalc.parser.Scanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class CalcWidget extends EditText {
	
	private int lastCursorPosition;
	private boolean cursorDraw = true;
	private OnCalcListener calcListener;
	private BCalcToken calc;

	public CalcWidget(Context context, AttributeSet attrs, int defStyle){
	    super(context, attrs,defStyle);
	}
	 
	public CalcWidget(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}

	public CalcWidget(Context context) {
		super(context);
		
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		super.onKeyUp(keyCode, event);
		
		if(keyCode == KeyEvent.KEYCODE_ENTER) {
			calcListener.onCalc(getText().toString(), Double.toString(calc.evaluate()));
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		// Parse the expression
	    if(lastCursorPosition != getSelectionEnd()) {
			lastCursorPosition = getSelectionEnd();
			Log.i("EditText", "Parse");
			
			calc = Parser.parseBCalcExpression(getText().toString());
			Log.i("EditText", calc+ "");
		}
		
		// Draw the background
		//super.onDraw(canvas);
		//Log.i("BCalc", getText().toString());
		
		// Draw the text
		Paint paint = new Paint();
	    paint.setStyle(Style.FILL_AND_STROKE);
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
//	    paint.setTextSize(20);
//	    canvas.drawText("99888", 10, 30, paint);
	    try {
	    	calc.onDraw(canvas, 10, 30, lastCursorPosition, cursorDraw);
		    if(cursorDraw && hasFocus()) {
		    	canvas.drawText("|", 15, 30, paint);
//	    		canvas.drawText("|", 26, 30, paint);
//	    		canvas.drawText("|", 37, 30, paint);
//	    		canvas.drawText("|", 48, 30, paint);
		    }
		    cursorDraw = !cursorDraw;
	    } catch (Exception e) {
	    	super.onDraw(canvas);
	    }
	}

	public OnCalcListener getCalcListener() {
		return calcListener;
	}

	public void setCalcListener(OnCalcListener calcListener) {
		this.calcListener = calcListener;
	}
}