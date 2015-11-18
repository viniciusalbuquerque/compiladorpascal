package classes;

import interfaces.Visitor;
import util.AST.AST;

public class Expression extends AST {
	
	private ArithmeticExpression arithmenticExpression;
	private int comparison;
	private ArithmeticExpression secondArithmetic;
	
	public Expression(ArithmeticExpression arithmeticExpression) {
		this.arithmenticExpression = arithmeticExpression;
	}
	
	public Expression(ArithmeticExpression arithmeticExpression, int comparison, ArithmeticExpression secondArithmetic) {
		this.arithmenticExpression = arithmeticExpression;
		this.comparison = comparison;
		this.secondArithmetic = secondArithmetic;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitExpression(this, obj);
	}

}
