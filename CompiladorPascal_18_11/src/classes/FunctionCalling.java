package classes;

import interfaces.Visitor;

public class FunctionCalling extends Statement {

	//TODO VERIFICAR CLASSE
	
	
	@Override
	public String toString(int level) {
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFunctionCalling(this, obj);
	}

}
