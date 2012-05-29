package com.bcalc.operations;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public abstract class BCalcToken {
	public final static int PADDING_LETTERS = 1;
	public final static int PADDING_WORDS = 3;
	public final static int WIDTH = 12;
	public final static int HEIGHT = 26;
	public final static int SIZE = 24;
	
	public abstract int getVisualHeight();
	public abstract int getVisualWidth();
	public abstract void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor);
	public abstract double evaluate();
//	public abstract String toString();
	
	public static void drawRectangule(Canvas canvas, Paint paint, int posX, int posY, int size) {
	    int color = paint.getColor();
	    paint.setColor(Color.BLUE);
	    
    	canvas.drawLine(posX, posY, posX+size*WIDTH+(size-1)*PADDING_LETTERS, posY, paint);
    	canvas.drawLine(posX+size*WIDTH+(size-1)*PADDING_LETTERS, posY, posX+size*WIDTH+(size-1)*PADDING_LETTERS, posY+HEIGHT, paint);
    	canvas.drawLine(posX, posY+HEIGHT, posX+size*WIDTH+(size-1)*PADDING_LETTERS, posY+HEIGHT, paint);
    	canvas.drawLine(posX, posY, posX, posY+HEIGHT, paint);
    	
    	paint.setColor(color);
	}
	
	public static void drawRectangule(Canvas canvas, Paint paint, int posX, int posY, int width, int height) {
//	    int color = paint.getColor();
//	    paint.setColor(Color.BLUE);
//	    
//    	canvas.drawLine(posX, posY, posX+width, posY, paint);
//    	canvas.drawLine(posX+width, posY, posX+width, posY+height, paint);
//    	canvas.drawLine(posX, posY+height, posX+width, posY+height, paint);
//    	canvas.drawLine(posX, posY, posX, posY+height, paint);
//    	
//    	paint.setColor(color);
	}

}
