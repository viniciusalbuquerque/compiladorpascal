package classes;

import interfaces.Visitor;

import java.util.List;

public class Conditional extends Statement {
	
	//TODO Botar Expression
	private Expression mExpression;
	private List<Statement> mIfStatetements;
	private List<Statement> mElseStatetements;
	
	public Conditional(Expression exp, List<Statement> ifStatements, List<Statement> elseStatements) {
		mExpression = exp;
		mIfStatetements = ifStatements;
		mElseStatetements = elseStatements;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitConditional(this, obj);
	}

}
