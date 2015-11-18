package classes;

import interfaces.Visitor;
import util.AST.AST;

public class ArithmeticExpression extends AST {
	
	//TODO CRIAR LISTAS
	
	private int operation;
	private Term term;
	
	public ArithmeticExpression(Term t) {
		this.term = t;
	}
	
	public ArithmeticExpression(int operation, Term t2) {
		this.operation = operation;
		this.term = t2;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitArithmeticExpression(this, obj);
	}

}
