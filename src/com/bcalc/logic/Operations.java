package com.bcalc.logic;

import java.lang.Math;

public class Operations {
	public static double sum(double x, double y) {
		return x + y;
	}
	
	public static double difference(double x, double y) {
		return x - y;
	}
	
	public static double multiplication(double x, double y) {
		return x * y;
	}
	
	public static double divition(double x, double y) {
		return x / y;
	}
	
	public static double power(double x, double y) {
		return Math.pow(x, y);
	}
}
