package com.bcalc.parser;

import android.util.Log;
import com.bcalc.operations.*;
import com.bcalc.operations.Number;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

// Name of the grammar.


public class Parser {
	public static final int _EOF = 0;
	public static final int _ident = 1;
	public static final int _number = 2;
	public static final int maxT = 13;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	public static BCalcToken parseBCalcExpression(String s)
{
    BCalcToken x = null;
    try {
        InputStream is = new ByteArrayInputStream(s.getBytes("UTF-8"));
        Scanner scanner = new Scanner(is);
        Parser parser = new Parser(scanner);
        x = parser.Parse(true);
        if (parser.errors.count > 0) 
        Log.e("ERROR", "Parsing errors: "+parser.errors.count);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    
    return x;
    //return parser._val;
}

public BCalcToken Parse(Boolean b) {
    la = new Token();
    la.val = "";        
    Get();
    BCalcToken x = BCalc();
    Expect(0);
    return x;

}


// Case insensitive.


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
	
	BCalcToken  BCalc() {
		BCalcToken  x;
		x = Expr();
		Log.i("Parser", "Processing... "); 
		return x;
	}

	BCalcToken  Expr() {
		BCalcToken  x;
		BCalcToken y; String op; 
		y = Term();
		x = y; 
		while (la.kind == 3 || la.kind == 4) {
			if (la.kind == 3) {
				Get();
			} else {
				Get();
			}
			op=t.val; 
			x = Term();
			x = new SingleLineOperation(op, y, x); 
		}
		while (la.kind == 5 || la.kind == 6) {
			if (la.kind == 5) {
				Get();
			} else {
				Get();
			}
			op=t.val; 
			x = new SingleTermOperation(op, x); 
		}
		return x;
	}

	BCalcToken  Term() {
		BCalcToken  x;
		BCalcToken y; String op; 
		y = Factor();
		x = y; 
		while (StartOf(1)) {
			if (la.kind == 7) {
				Get();
			} else if (la.kind == 8) {
				Get();
			} else if (la.kind == 6) {
				Get();
			} else {
				Get();
			}
			op=t.val; 
			y = Factor();
			if(op.equals("/") || op.equals("^")) {
			 x = new MultipleLineOperation(op, x, y);
			} else {
			  x = new SingleLineOperation(op, x, y);
			} 
			                                                                          
		}
		return x;
	}

	BCalcToken  Factor() {
		BCalcToken  x;
		x = null; BCalcToken y; String op = ""; 
		if (la.kind == 2) {
			Get();
			x = new Number(t.val); 
		} else if (la.kind == 1) {
			y = Name();
			x = y; 
		} else if (la.kind == 4) {
			Get();
			op=t.val; 
			y = Expr();
			x = new SingleTermOperation(op, y); 
		} else if (la.kind == 10) {
			Get();
			op=op+t.val; 
			y = Expr();
			Expect(11);
			op=op+t.val; 
			x = new MultipleLineOperation(op, y); 
		} else SynErr(14);
		return x;
	}

	BCalcToken  Name() {
		BCalcToken  x;
		ArrayList<BCalcToken> args = null; String name; 
		Expect(1);
		name = t.val; 
		if (la.kind == 10) {
			Get();
			args = new ArrayList<BCalcToken>(); 
			if (StartOf(2)) {
				args = ArgList();
			}
			Expect(11);
		}
		x = new Function(name, args); 
		return x;
	}

	ArrayList  ArgList() {
		ArrayList  args;
		args = new ArrayList<BCalcToken>();BCalcToken y; 
		y = Expr();
		args.add(y);  
		while (la.kind == 12) {
			Get();
			y = Expr();
			args.add(y);  
		}
		return args;
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		BCalc();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x, x,x,x,x, x,x,x},
		{x,x,x,x, x,x,T,T, T,T,x,x, x,x,x},
		{x,T,T,x, T,x,x,x, x,x,T,x, x,x,x}

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
			case 1: s = "ident expected"; break;
			case 2: s = "number expected"; break;
			case 3: s = "\"+\" expected"; break;
			case 4: s = "\"-\" expected"; break;
			case 5: s = "\"!\" expected"; break;
			case 6: s = "\"%\" expected"; break;
			case 7: s = "\"*\" expected"; break;
			case 8: s = "\"/\" expected"; break;
			case 9: s = "\"^\" expected"; break;
			case 10: s = "\"(\" expected"; break;
			case 11: s = "\")\" expected"; break;
			case 12: s = "\",\" expected"; break;
			case 13: s = "??? expected"; break;
			case 14: s = "invalid Factor"; break;
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
