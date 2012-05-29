package com.bcalc.operations;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Function extends BCalcToken {
	
	private String name;
	private ArrayList<BCalcToken> args;
	
	public Function(String name, ArrayList<BCalcToken> args) {
		// TODO Auto-generated constructor stub
		this.setName(name);
		this.setArgs(args);
	}

	@Override
	public int getVisualHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVisualWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onDraw(Canvas canvas, Paint paint, int padLeft, int padUp, int cursorIndex, boolean Cursor) {
		// TODO Auto-generated method stub

	}

	@Override
	public double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<BCalcToken> getArgs() {
		return args;
	}

	public void setArgs(ArrayList<BCalcToken> args) {
		this.args = args;
	}

}
