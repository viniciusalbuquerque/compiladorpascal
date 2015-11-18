package classes;

import interfaces.Visitor;

public class Continue extends Statement {

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitContinue(this, obj);
	}

}
