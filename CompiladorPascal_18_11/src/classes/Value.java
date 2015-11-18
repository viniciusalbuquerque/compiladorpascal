package classes;

import interfaces.Visitor;
import util.AST.AST;

public class Value extends AST {
	
	private int type;
	private String spelling;
	
	public Value(int type, String spelling) {
		this.type = type;
		this.spelling = spelling;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitValue(this, obj);
	}

}
