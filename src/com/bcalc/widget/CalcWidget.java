package com.bcalc.widget;

import com.bcalc.operations.BCalcToken;
import com.bcalc.parser.Parser;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CalcWidget extends EditText {
	
	private int lastCursorPosition;
	private boolean cursorDraw = true;
	private OnCalcListener calcListener;
	private BCalcToken calc;
	protected InputMethodManager inputMethodManager;
//	private int widthMeasureSpec, heightMeasureSpec;
	 
	public CalcWidget(Context context, AttributeSet attrs, int defStyle){
	    super(context, attrs,defStyle);
		init();
	}
	
	public CalcWidget(Context context, AttributeSet attrs) {
	    super(context, attrs);
		init();
	}

	public CalcWidget(Context context) {
		super(context);
		init();
	}
	
	public void init() {
//		setInputType(InputType.TYPE_CLASS_NUMBER);
		addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				Log.i("CalcWidget ", "Parse");
				calc = Parser.parseBCalcExpression(getText().toString());
				Log.i("EditText", calc+ "");
//				onMeasure(widthMeasureSpec, heightMeasureSpec);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		super.onKeyUp(keyCode, event);
		
		if(keyCode == KeyEvent.KEYCODE_ENTER) {
			if(calc != null) {
				calcListener.onCalc(
						this,
						getText().toString(), 
						Double.toString(calc.evaluate()));
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		this.widthMeasureSpec = widthMeasureSpec;
//		this.heightMeasureSpec = heightMeasureSpec;
		
	    int width = calculateWidth(widthMeasureSpec);
	    int height = calculateHeight(heightMeasureSpec);

	    Log.i("Height", height + ""); 
	 
	    setMeasuredDimension(width, height);
	}
	 
	private int calculateHeight(int limitesSpec)
	{
		int res = BCalcToken.HEIGHT + 25;

		// Calculate the height
		if(calc != null) {
			res = calc.getVisualHeight() + 25;
		}
	 
	    return res;
	}
	 
	private int calculateWidth(int limitesSpec)
	{
		int res = 0;

		if(calc != null) {
			res = calc.getVisualWidth();
		}
		
	    int mode = MeasureSpec.getMode(limitesSpec);
	    int limit = MeasureSpec.getSize(limitesSpec);
	 
	    if (mode == MeasureSpec.AT_MOST) {
	        res = limit;
	    }
	    else if (mode == MeasureSpec.EXACTLY) {
	        res = limit;
	    }
	 
	    return res;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		// Parse the expression
	    if(lastCursorPosition != getSelectionEnd()) {
			lastCursorPosition = getSelectionEnd();
		}
		
		// Draw the background
		//super.onDraw(canvas);
		//Log.i("BCalc", getText().toString());
		
		// Draw the text
		Paint paint = new Paint();
	    paint.setStyle(Style.FILL_AND_STROKE);
    	paint.setColor(Color.BLACK);
	    paint.setAntiAlias(true);
	    paint.setTextSize(BCalcToken.SIZE);
	    
//	    Draw text test
//	    canvas.drawText("99888", 10, 30, paint);
	    
//	    	int y = 9;
//	    	int height = 26;
	    	
//	    	canvas.drawLine(12, y, 50, y, paint);
//	    	canvas.drawLine(12, y+height, 50, y+height, paint);

//	    	int x = 11;
//	    	int pad = 1;
//	    	int width = 10;
	    	
//	    	canvas.drawLine(x, 8, x+width, 8, paint);
//	    	canvas.drawLine(x, 35, x+width, 35, paint);
//	    	canvas.drawLine(x, 8, x, 35, paint);
//	    	canvas.drawLine(x+width, 8, x+width, 35, paint);
//	    	canvas.drawLine(x+width+pad, 8, x+2*width+pad, 8, paint);
//	    	canvas.drawLine(x+width+pad, 35, x+2*width+pad, 35, paint);
//	    	canvas.drawLine(x+2*width+2*pad, 8, x+3*width+2*pad, 8, paint);
//	    	canvas.drawLine(x+2*width+2*pad, 35, x+3*width+2*pad, 35, paint);
	    	
//	    	int size = 10;
//	    	canvas.drawLine(x, y, x+size*width+(size-1)*pad, y, paint);
//	    	canvas.drawLine(x+size*width+(size-1)*pad, y, x+size*width+(size-1)*pad, y+height, paint);
//	    	canvas.drawLine(x, y+height, x+size*width+(size-1)*pad, y+height, paint);
//	    	canvas.drawLine(x, y, x, y+height, paint);
	    	
//	    Canvas c = canvas.;
	    try {
	    	calc.onDraw(canvas, paint, 11, 9, lastCursorPosition, cursorDraw);
		    if(cursorDraw && hasFocus()) {
		    	canvas.drawText("|", 15, 30, paint);
//	    		canvas.drawText("|", 26, 30, paint);
//	    		canvas.drawText("|", 37, 30, paint);
//	    		canvas.drawText("|", 48, 30, paint);
		    }
		    cursorDraw = !cursorDraw;
	    } catch (Exception e) {
//	    	Log.e(this + "", e.getMessage()+"");
	    	
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
