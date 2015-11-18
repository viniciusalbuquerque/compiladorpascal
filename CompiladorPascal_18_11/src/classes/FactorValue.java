package classes;

import interfaces.Visitor;

public class FactorValue extends Factor {
	
	private Value value;
	
	public FactorValue(Value value) {
		this.value = value;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFactorValue(this, obj);
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
