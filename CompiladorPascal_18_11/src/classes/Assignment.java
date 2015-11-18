package classes;

import interfaces.Visitor;

public class Assignment extends Statement {
	
	private Expression expression;
	
	public Assignment(Expression e) {
		this.expression = e;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitAssignment(this, obj);
	}

}
