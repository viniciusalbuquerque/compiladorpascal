package scanner;

import parser.GrammarSymbols;
import compiler.Properties;
import compiler.Compiler;
import util.Arquivo;

/**
 * Scanner class
 * @version 2010-september-04
 * @discipline Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Scanner {

	// The file object that will be used to read the source code
	private Arquivo file;
	// The last char read from the source code
	private char currentChar;
	// The kind of the current token
	private int currentKind;
	// Buffer to append characters read from file
	private StringBuffer currentSpelling;
	// Current line and column in the source file
	private int line, column;
	
	/**
	 * Default constructor
	 */
	public Scanner() {
		this.file = new Arquivo(Properties.sourceCodeLocation);		
		this.line = 0;
		this.column = 0;
		this.currentChar = this.file.readChar();
	}
	
	/**
	 * Returns the next token
	 * @return
	 */ //TODO
	public Token getNextToken() {
			// Initializes the string buffer
			// Ignores separators
			// Clears the string buffer
			// Scans the next token
		// Creates and returns a token for the lexema identified
		currentSpelling = new StringBuffer();
		Token token = null;
	
		try {
			while(isSeparator(currentChar)) {
				scanSeparator();
			}
			currentKind = scanToken();
			
			if(currentKind == GrammarSymbols.ID) {
				if(GrammarSymbols.reservedWords.containsKey(currentSpelling.toString())) {
					currentKind = GrammarSymbols.reservedWords.get(currentSpelling.toString());
				}				
			}
			token = new Token(currentKind, currentSpelling.toString(), line, column);
		} catch (LexicalException e) {
			e.printStackTrace();
		}
//		System.out.println("|" + currentSpelling.toString() + "|");
		return token;
	}
	
	/**
	 * Returns if a character is a separator
	 * @param c
	 * @return
	 */
	private boolean isSeparator(char c) {
		if ( c == '#' || c == ' ' || c == '\n' || c == '\t' ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Reads (and ignores) a separator
	 * @throws LexicalException
	 */ //TODO
	private void scanSeparator() throws LexicalException {
		// If it is a comment line
			// Gets next char
			// Reads characters while they are graphics or '\t'
			// A command line should finish with a \n
		if(currentChar == '#') {
			while(isGraphic(currentChar) || currentChar == '\t') {
				getNextCharSeparator();
			}
		} else {
			getNextCharSeparator();
		}
	}
	
	private void getNextCharSeparator() {
		// Reads the next one
		this.currentChar = this.file.readChar();
		// Increments the line and column
		this.incrementLineColumn();
	}
	
	
	/**
	 * Gets the next char
	 */
	private void getNextChar() {
		// Appends the current char to the string buffer
		this.currentSpelling.append(this.currentChar);
		// Reads the next one
		this.currentChar = this.file.readChar();
		// Increments the line and column
		this.incrementLineColumn();
	}
	
	/**
	 * Increments line and column
	 */
	private void incrementLineColumn() {
		// If the char read is a '\n', increments the line variable and assigns 0 to the column
		if ( this.currentChar == '\n' ) {
			this.line++;
			this.column = 0;
		// If the char read is not a '\n' 
		} else {
			// If it is a '\t', increments the column by 4
			if ( this.currentChar == '\t' ) {
				this.column = this.column + 4;
			// If it is not a '\t', increments the column by 1
			} else {
				this.column++;
			}
		}
	}
	
	/**
	 * Returns if a char is a digit (between 0 and 9)
	 * @param c
	 * @return
	 */
	private boolean isDigit(char c) {
		if ( c >= '0' && c <= '9' ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns if a char is a letter (between a and z or between A and Z)
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c) {
		if ( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns if a char is a graphic (any ASCII visible character)
	 * @param c
	 * @return
	 */
	private boolean isGraphic(char c) {
		if ( c >= ' ' && c <= '~' ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Scans the next token
	 * Simulates the DFA that recognizes the language described by the lexical grammar
	 * @return
	 * @throws LexicalException
	 */ //TODO
	private int scanToken() throws LexicalException {
		// The initial automata state is 0
		// While loop to simulate the automata
		int currentState = 0;
		
		while(true) {
			switch(currentState) {
			case GrammarSymbols.INITIAL:
				if(isDigit(currentChar)) {
					currentState = GrammarSymbols.NUM;
				} else if(isLetter(currentChar)) {
					currentState = GrammarSymbols.ID;
				} else if(currentChar == '=') {
					currentState = GrammarSymbols.EQUAL;
				} else if(currentChar == ':') {
					currentState = GrammarSymbols.COL;
				} else if(currentChar == '<') {
					currentState = GrammarSymbols.LT;
				} else if(currentChar == '>') {
					currentState = GrammarSymbols.GT;
				} else if(currentChar == '+') {
					currentState = GrammarSymbols.ADD;
				} else if(currentChar == '-') {
					currentState = GrammarSymbols.SUB;
				} else if(currentChar == '*') {
					currentState = GrammarSymbols.MULT;
				} else if(currentChar == '/') {
					currentState = GrammarSymbols.DIV;
				} else if(currentChar == ',') {
					currentState = GrammarSymbols.COMMA;
				} else if(currentChar == ';') {
					currentState = GrammarSymbols.SC;
				} else if(currentChar == '#') {
					currentState = GrammarSymbols.COMMENT;
				} else if(currentChar == '(') {
					currentState = GrammarSymbols.LP;
				} else if(currentChar == ')') {
					currentState = GrammarSymbols.RP;
				} else if(currentChar == '\000') {
					currentState = GrammarSymbols.EOT;
				} else {
					currentState = GrammarSymbols.ERROR;
				}
				if(currentState != GrammarSymbols.ERROR)
					getNextChar();
				break;
			case GrammarSymbols.NUM:
				if(isDigit(currentChar)) {
					getNextChar();
				} else {
					return GrammarSymbols.NUM;
				}
				break;
			case GrammarSymbols.ID:
				if(isLetter(currentChar) || isDigit(currentChar)) {
					getNextChar();
				} else {
					return GrammarSymbols.ID;
				}
				break;
			case GrammarSymbols.EQUAL:
				return GrammarSymbols.EQUAL;
			case GrammarSymbols.COL:
				if(currentChar == '=') {
					currentState = GrammarSymbols.ASG;
				} else {
					return GrammarSymbols.COL;
				}
				getNextChar();
				break;
			case GrammarSymbols.LT:
				if(currentChar == '>') {
					currentState = GrammarSymbols.DIFF;
				} else if(currentChar == '=') {
					currentState = GrammarSymbols.LTE;
				} else {
					return GrammarSymbols.LT;
				}
				getNextChar();
				break;
			case GrammarSymbols.LTE:
				return GrammarSymbols.LTE;
			case GrammarSymbols.DIFF:
				return GrammarSymbols.DIFF;
			case GrammarSymbols.GT:
				if(currentChar == '=') {
					currentState = GrammarSymbols.GTE;
					getNextChar();
				} else {
					return GrammarSymbols.GT;
				}
				break;
				
			case GrammarSymbols.GTE:
				return GrammarSymbols.GTE;
			
			case GrammarSymbols.ADD:
				return GrammarSymbols.ADD;
			
			case GrammarSymbols.SUB:
				return GrammarSymbols.SUB;
			
			case GrammarSymbols.MULT:
				return GrammarSymbols.MULT;
				
			case GrammarSymbols.DIV:
				return GrammarSymbols.DIV;
				
			case GrammarSymbols.COMMA:
				return GrammarSymbols.COMMA;
			
			case GrammarSymbols.SC:
				return GrammarSymbols.SC;
				
			case GrammarSymbols.COMMENT:
				return GrammarSymbols.COMMENT;
			
			case GrammarSymbols.LP:
			 	return GrammarSymbols.LP;
			 	
			case GrammarSymbols.RP:
				return GrammarSymbols.RP;
				
			case GrammarSymbols.ASG:
				return GrammarSymbols.ASG;
				
			case GrammarSymbols.EOT:
				return GrammarSymbols.EOT;
			
			case GrammarSymbols.ERROR:
				throw new LexicalException("Erro Lexico", currentChar, line, column);
			}
		}
	
	}
	
	
}
