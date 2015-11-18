package parser;

import java.util.ArrayList;
import java.util.List;

import classes.ArithmeticExpression;
import classes.Assignment;
import classes.Break;
import classes.Conditional;
import classes.Continue;
import classes.DecVar;
import classes.Declarations;
import classes.Expression;
import classes.Factor;
import classes.FactorExpression;
import classes.FactorFunctionCalling;
import classes.FactorID;
import classes.FactorValue;
import classes.Function;
import classes.FunctionCalling;
import classes.FunctionDeclaration;
import classes.ParamDeclaration;
import classes.Program;
import classes.Return;
import classes.Statement;
import classes.Term;
import classes.Value;
import classes.While;
import scanner.Scanner;
import scanner.Token;
import util.AST.AST;

/**
 * Parser class
 * @version 2010-august-29
 * @discipline Projeto de Compiladores
 * @author Gustavo H P Carvalho
 * @email gustavohpcarvalho@ecomp.poli.br
 */
public class Parser {

	// The current token
	private Token currentToken = null;
	// The scanner
	private Scanner scanner = null;
	
	/**
	 * Parser constructor
	 */
	public Parser() {
		// Initializes the scanner object
		this.scanner = new Scanner();
		currentToken = scanner.getNextToken();
	}
	
	/**
	 * Veririfes if the current token kind is the expected one
	 * @param kind
	 * @throws SyntacticException
	 */
	private void accept(int kind) throws SyntacticException {
		// If the current token kind is equal to the expected
			// Gets next token
		// If not
			// Raises an exception
		
		if(currentToken.getKind() == kind) {
			currentToken = scanner.getNextToken();
		} else {
			throwException();
		}
	}
	
	private void throwException() throws SyntacticException {
		throw new SyntacticException("Syntactic Exception", currentToken);
	}
	
	/**
	 * Gets next token
	 */
	private void acceptIt() {
		currentToken = scanner.getNextToken();
	}

	/**
	 * Verifies if the source program is syntactically correct
	 * @throws SyntacticException
	 */
	public AST parse() throws SyntacticException {
		Program p = parseProgram();
		return p;
	}
	
	private Program parseProgram() throws SyntacticException {
//		acceptIt();
		accept(GrammarSymbols.PROGRAM);
		accept(GrammarSymbols.ID);
		Declarations decs = null;
		if(currentToken.getKind() == GrammarSymbols.VAR)
			decs = parseDeclarations();
		
		List<Function> functions = new ArrayList<Function>();
		while(currentToken.getKind() != GrammarSymbols.EOT) {
			functions.add(parseFunction());
		}
		return new Program(functions, decs);
	}
	
	private Declarations parseDeclarations() throws SyntacticException {
		ArrayList<DecVar> decvars = new ArrayList<DecVar>();
		
		while(currentToken.getKind() == GrammarSymbols.VAR) {
			acceptIt();
			decvars.add(parseDecVar());
		}
		return new Declarations(decvars);
	}
	
	private DecVar parseDecVar() throws SyntacticException {
		ArrayList<String> ids = new ArrayList<String>();
		if(currentToken.getKind() == GrammarSymbols.ID) {
			ids.add(currentToken.getSpelling());
			acceptIt();
		} else {
			throwException();
		}
		if(currentToken.getKind() == GrammarSymbols.COMMA) {
			ids.addAll(parseListID());
		}
		accept(GrammarSymbols.COL);
		int type = parseType();
		accept(GrammarSymbols.SC);
		return new DecVar(ids,type);
	}
	
	private ArrayList<String> parseListID() throws SyntacticException {
		ArrayList<String> ids = new ArrayList<String>();
		while(currentToken.getKind() == GrammarSymbols.COMMA) {
			acceptIt();
			ids.add(currentToken.getSpelling());
			accept(GrammarSymbols.ID);
		}
		return ids;
	}

	private Function parseFunction() throws SyntacticException {
		FunctionDeclaration declaration = parseFunctionDeclaration();
		List<Statement> statements = parseBody();
		accept(GrammarSymbols.SC);
		return new Function(declaration, statements);
	}
	
	private FunctionDeclaration parseFunctionDeclaration() throws SyntacticException {
		accept(GrammarSymbols.FUNCTION);
		String id = currentToken.getSpelling();
		accept(GrammarSymbols.ID);
		accept(GrammarSymbols.LP);
		ParamDeclaration paramDec = parseParamDeclaration();
		accept(GrammarSymbols.RP);
		accept(GrammarSymbols.COL);
		int type = parseFunctionType();
		accept(GrammarSymbols.SC);
		return new FunctionDeclaration(id, paramDec, type);
	}
	
	private int parseFunctionType() throws SyntacticException {
		int type;
		if(currentToken.getKind() == GrammarSymbols.VOID) {
			type = GrammarSymbols.VOID;
			acceptIt();
		} else {
			type = parseType();
		}
		return type;
	}

	private ParamDeclaration parseParamDeclaration() throws SyntacticException {
		List<DecVar> decVars = new ArrayList<DecVar>();
		while(currentToken.getKind() != GrammarSymbols.RP) {
			decVars.add(parseDecVar());
		}
		return new ParamDeclaration(decVars);
	}
	
	private List<Statement> parseBody() throws SyntacticException {
		accept(GrammarSymbols.BEGIN);
		List<Statement> sts = parseListStatement();
		accept(GrammarSymbols.END);
		return sts;
	}
	
	private Statement parseStatement() throws SyntacticException {
		Statement statement = null;
		if(currentToken.getKind() == GrammarSymbols.ID) {
			acceptIt();
			if(currentToken.getKind() == GrammarSymbols.LP) {
				statement = parseFunctionCalling();
			} else {
				if(currentToken.getKind() == GrammarSymbols.COMMA) {
					parseListID();
				}
				
				if(currentToken.getKind() == GrammarSymbols.ASG) {
					statement = parseASG();
					accept(GrammarSymbols.SC);
				} else {
					statement = parseDecVarStatement();
				}
			}
		} else {
			switch(currentToken.getKind()) {
			case GrammarSymbols.WHILE:
				statement = parseWhile();
				break;
			case GrammarSymbols.IF:
				statement = parseConditional();
				break;
			case GrammarSymbols.CONTINUE:
				acceptIt();
				accept(GrammarSymbols.SC);
				statement = new Continue();
				break;
			case GrammarSymbols.BREAK:
				acceptIt();
				accept(GrammarSymbols.SC);
				statement = new Break();
				break;
			case GrammarSymbols.RETURN:
				statement = parseReturn();
				accept(GrammarSymbols.SC);
				break;
//			case GrammarSymbols.BEGIN:
//				parseBody();
//				break;
			default:
				throwException();
				break;
			}
		}
		return statement;
	}

	private DecVar parseDecVarStatement() throws SyntacticException {
		accept(GrammarSymbols.COL);
		int type = parseType();
		return new DecVar(type);
	}

	private int parseType() throws SyntacticException {
		int type;
		if(currentToken.getKind() == GrammarSymbols.BOOL) {
			acceptIt();
			type = GrammarSymbols.BOOL;
		} else {
			type = GrammarSymbols.INT;
			accept(GrammarSymbols.INT);
		}
		return type;
	}

	private Assignment parseASG() throws SyntacticException {
		accept(GrammarSymbols.ASG);
		Expression e = parseExpression();
		return new Assignment(e);
	}

	private While parseWhile() throws SyntacticException {
		accept(GrammarSymbols.WHILE);
		parseExpression();
		accept(GrammarSymbols.DO);
		List<Statement> sts = parseBody();
		return new While(sts);
	}
	
	private List<Statement> parseListStatement() throws SyntacticException{
		List<Statement> statements = new ArrayList<Statement>();
		while(currentToken.getKind() != GrammarSymbols.END && 
				currentToken.getKind() != GrammarSymbols.ELSE) {
			statements.add(parseStatement());
		}
		return statements;
	}

	private Conditional parseConditional() throws SyntacticException {
		accept(GrammarSymbols.IF);
		Expression e = parseExpression();
		accept(GrammarSymbols.THEN);
		accept(GrammarSymbols.BEGIN);
		List<Statement> ifStatements = new ArrayList<Statement>();
		if(currentToken.getKind() != GrammarSymbols.ELSE && 
				currentToken.getKind() != GrammarSymbols.END) {
			ifStatements = parseListStatement();
		}
		List<Statement> elseStatements = new ArrayList<Statement>();
		if(currentToken.getKind() == GrammarSymbols.ELSE) {
			acceptIt();
			elseStatements = parseListStatement();
		} 
		accept(GrammarSymbols.END);
		return new Conditional(e, ifStatements, elseStatements);
	}

	private Return parseReturn() throws SyntacticException {
		accept(GrammarSymbols.RETURN);
		Expression e = parseExpression();
		return new Return(e);
	}

	private Expression parseExpression() throws SyntacticException{
		Expression e = null;
		int comparison = -1;
		
		ArithmeticExpression a1 = parseArithmeticExpression();
		if(currentToken.getKind() == GrammarSymbols.EQUAL || currentToken.getKind() == GrammarSymbols.DIFF ||
				currentToken.getKind() == GrammarSymbols.LT || currentToken.getKind() == GrammarSymbols.GT ||
				currentToken.getKind() == GrammarSymbols.GTE || currentToken.getKind() == GrammarSymbols.LTE) {
			comparison = currentToken.getKind();
			acceptIt();
			ArithmeticExpression a2 = parseArithmeticExpression();
			e = new Expression(a1, comparison, a2);
		} else {
			e = new Expression(a1);
		}
		return e;
	}

	private ArithmeticExpression parseArithmeticExpression() throws  SyntacticException {
		int operator = -1;
		ArithmeticExpression a = null;
		if(currentToken.getKind() == GrammarSymbols.ADD || currentToken.getKind() == GrammarSymbols.SUB) {
			operator = currentToken.getKind();
			acceptIt();
		}
		
		Term t = parseTerm();
		if(operator != -1) {
			a = new ArithmeticExpression(operator, t);
		} else {
			a = new ArithmeticExpression(t);
		}
		return a;
		
	}

	private Term parseTerm() throws SyntacticException {
		int operation = -1;
		Term t = null;
		if(currentToken.getKind() == GrammarSymbols.MULT || currentToken.getKind() == GrammarSymbols.DIV) {
			operation = currentToken.getKind();
			acceptIt();
		}
		
		Factor f = parseFactor();
		
		if(operation != -1) {
			t = new Term(operation, f);
		} else {
			t = new Term(f);
		}
		return t;
	}

	private Factor parseFactor() throws SyntacticException {
		Factor f = null;
		if(currentToken.getKind() == GrammarSymbols.ID) {
			String id = currentToken.getSpelling();
			acceptIt();
			if(currentToken.getKind() == GrammarSymbols.LP) {
				FunctionCalling funcCalling = parseFunctionCalling();
				f = new FactorFunctionCalling(id, funcCalling);
			} else {
				f = new FactorID(id);
			}
		} else if(currentToken.getKind() == GrammarSymbols.LP) {
			acceptIt();
			Expression e = parseExpression();
			accept(GrammarSymbols.RP);
			f = new FactorExpression(e);
		} else {
			f = new FactorValue(parseValue());
			if(currentToken.getKind() == GrammarSymbols.ADD) {
				parseArithmeticExpression();
			}
		}
		return f;
	}
	
	private FunctionCalling parseFunctionCalling() throws SyntacticException {
		//TODO
		accept(GrammarSymbols.LP);
		if(currentToken.getKind() == GrammarSymbols.ID || currentToken.getKind() == GrammarSymbols.NUM) {
			acceptIt();
			if(currentToken.getKind() == GrammarSymbols.COMMA) {
				parseValidFunc();
			}
		}
		accept(GrammarSymbols.RP);
		accept(GrammarSymbols.SC);
		return new FunctionCalling();
	}

	private void parseValidFunc() throws SyntacticException {
		while(currentToken.getKind() == GrammarSymbols.COMMA) {
			acceptIt();
			if(currentToken.getKind() == GrammarSymbols.ID || currentToken.getKind() == GrammarSymbols.NUM) {
				acceptIt();
			} else {
				throwException();
			}
		}
	}

	private Value parseValue() throws SyntacticException {
		Value v = null;
		if(currentToken.getKind() == GrammarSymbols.NUM) {
			v = new Value(currentToken.getKind(), currentToken.getSpelling());
			acceptIt();
		} else {
			v = parseBool();
		}
		return v;
	}
	
	private Value parseBool() throws SyntacticException {
		Value v = new Value(currentToken.getKind(), currentToken.getSpelling());
		if(currentToken.getKind() == GrammarSymbols.TRUE) {
			acceptIt();
		} else {
			accept(GrammarSymbols.FALSE);
		}
		return v;
	}
	
}