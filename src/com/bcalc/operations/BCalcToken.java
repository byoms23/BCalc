package com.bcalc.operations;

import android.graphics.Canvas;

public interface BCalcToken {
	final int PADDING = 3;
	final int WIDTH = 11;
	
	public int getVisualHeight();
	public int getVisualWidth();
	public void onDraw(Canvas canvas, int padLeft, int padUp, int cursorIndex, boolean Cursor);
	public double evaluate();
	public String toString();
}
