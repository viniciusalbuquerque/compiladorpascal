package parser;

import java.util.HashMap;

/**
 * This class contains codes for each grammar terminal
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class GrammarSymbols {

	// Language terminals (starts from 0)
//	public static final int ID = 0;
	public static final int VOID = 100;
	public static final int INT = 200;
	public static final int BOOL = 300;
	public static final int IF = 400;
	public static final int ELSE = 500;
	public static final int WHILE = 600;
	public static final int RETURN = 700;
	public static final int BREAK = 800;
	public static final int CONTINUE = 900;
	public static final int WRITELN = 1000;
	public static final int FALSE = 1100;
	public static final int TRUE = 1200;
	public static final int PROGRAM = 1300;
	public static final int FUNCTION = 1400;
	public static final int BEGIN = 1500;
	public static final int END = 1600;
	public static final int DO = 1700;
	public static final int THEN = 1800;
	public static final int VAR = 1900;
//	public static final int EOT = 1000;
	
	public static final int INITIAL = 0;
	public static final int NUM = 1;
	public static final int ID = 2;
	public static final int EQUAL = 3;
	public static final int COL = 4;
	public static final int ASG = 5;
	public static final int LT = 6;
	public static final int DIFF = 7;
	public static final int LTE = 8;
	public static final int GT = 9;
	public static final int GTE = 10;
	public static final int ADD = 11;
	public static final int SUB = 12;
	public static final int MULT = 13;
	public static final int DIV = 14;
	public static final int COMMA = 15;
	public static final int SC = 16;
	public static final int COMMENT = 17;
	public static final int LP = 18;
	public static final int RP = 19;
	public static final int EOT = 20;
	public static final int ERROR = 21;
	
	
	public static HashMap<String, Integer> reservedWords = new HashMap<String, Integer>(19);
	
	static {
		reservedWords.put("void", VOID);
		reservedWords.put("integer", INT);
		reservedWords.put("boolean", BOOL);
		reservedWords.put("if", IF);
		reservedWords.put("else", ELSE);
		reservedWords.put("while", WHILE);
		reservedWords.put("return", RETURN);
		reservedWords.put("break", BREAK);
		reservedWords.put("continue", CONTINUE);
		reservedWords.put("writeln", WRITELN);
		reservedWords.put("false", FALSE);
		reservedWords.put("true", TRUE);
		reservedWords.put("program", PROGRAM);
		reservedWords.put("function", FUNCTION);
		reservedWords.put("begin", BEGIN);
		reservedWords.put("end", END);
		reservedWords.put("do", DO);
		reservedWords.put("then", THEN);
		reservedWords.put("var", VAR);
	}
	
}
