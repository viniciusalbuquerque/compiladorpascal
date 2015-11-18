package classes;

import interfaces.Visitor;

import java.util.List;

public class While extends Statement {
	
	private List<Statement> mListStatement;
	
	public While(List<Statement> statements) {
		mListStatement = statements;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitWhile(this, obj);
	}

}
