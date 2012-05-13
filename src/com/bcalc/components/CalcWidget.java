package com.bcalc.components;

import java.io.FileNotFoundException;

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
			
		}
		
		return false;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		// Parse the expression
	    Log.i("EditText", Integer.toString(getSelectionStart()));
		if(lastCursorPosition != getSelectionEnd()) {
			lastCursorPosition = getSelectionEnd();
			Log.i("EditText", "Parse");
			
//			String inFileName;
//			String outFileName;
//			Scanner scanner = new Scanner(inFileName);
//			Parser parser = new Parser(scanner);
//			parser.Parse();
//				System.out.println(parser.errors.count + " errors detected");
		}
		
		// Draw the background
		//super.onDraw(canvas);
		//Log.i("BCalc", getText().toString());
		
		// Draw the text
		Paint paint = new Paint();
	    paint.setStyle(Style.FILL_AND_STROKE);
	    paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    
	    paint.setTextSize(25);
	    canvas.drawText("99", 5, 35, paint);
	    
	    if(cursorDraw && hasFocus()) {
	    	canvas.drawText("|", 5, 35, paint);
	    }
	    cursorDraw = !cursorDraw;
	}
}
