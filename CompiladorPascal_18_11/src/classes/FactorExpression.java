package classes;

import interfaces.Visitor;

public class FactorExpression extends Factor {
	
	private Expression expression;
	
	public FactorExpression(Expression exp) {
		expression = exp;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFactorExpression(this, obj);
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
