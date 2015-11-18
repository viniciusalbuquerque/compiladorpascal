package classes;

import interfaces.Visitor;

public class FactorFunctionCalling extends Factor {
	
	private String id;
	private FunctionCalling funcCalling;
	
	public FactorFunctionCalling(String id, FunctionCalling fc) {
		this.id = id;
		this.funcCalling = fc;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFactorFunctionCalling(this, obj);
	}

	@Override
	public String toString(int level) {
		return null;
	}

}
