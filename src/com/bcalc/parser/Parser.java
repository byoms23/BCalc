package com.bcalc.parser;

import android.util.Log;

// Name of the grammar.


public class Parser {
	public static final int _EOF = 0;
	public static final int _number = 1;
	public static final int maxT = 6;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void BCalc() {
		int x; 
		x = Expr();
		Log.i("Parser", Integer.toString(x)); 
	}

	int  Expr() {
		int  x;
		int y; 
		x = Term();
		while (la.kind == 2) {
			Get();
			y = Term();
			x = x + y; 
		}
		return x;
	}

	int  Term() {
		int  x;
		int y; 
		x = Factor();
		while (la.kind == 3) {
			Get();
			y = Factor();
			x = x*y; 
		}
		return x;
	}

	int  Factor() {
		int  x;
		x = 0; 
		if (la.kind == 1) {
			Get();
			x = Integer.parseInt(t.val); 
		} else if (la.kind == 4) {
			Get();
			x = Expr();
			Expect(5);
		} else SynErr(7);
		return x;
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		BCalc();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x}

	};


	public String ParseErrors(){
			java.io.PrintStream oldStream = System.out;
		
		java.io.OutputStream out = new java.io.ByteArrayOutputStream();
		java.io.PrintStream newStream = new java.io.PrintStream(out);
		
		errors.errorStream = newStream;
		
		Parse();

		String errorStream = out.toString();
		errors.errorStream = oldStream;

		return errorStream;


	}
} // end Parser


class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text
	
	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}
	
	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "number expected"; break;
			case 2: s = "\"+\" expected"; break;
			case 3: s = "\"*\" expected"; break;
			case 4: s = "\"(\" expected"; break;
			case 5: s = "\")\" expected"; break;
			case 6: s = "??? expected"; break;
			case 7: s = "invalid Factor"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {	
		printMsg(line, col, s);
		count++;
	}
	
	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}
	
	public void Warning (int line, int col, String s) {	
		printMsg(line, col, s);
	}
	
	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
