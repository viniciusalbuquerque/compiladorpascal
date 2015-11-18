package classes;

import interfaces.Visitor;

public class FactorID extends Factor {
	
	private String id;

	public FactorID(String id) {
		this.id = id;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFactorID(this, obj);
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
