package classes;

import interfaces.Visitor;

import java.util.ArrayList;

public class DecVar extends Statement {

	private ArrayList<String> ids;
	private int type;
	
	public DecVar(ArrayList<String> ids, int type) {
		this.ids = ids;
		this.type = type;
	}
	
	public DecVar(int type) {
		this.type = type;
	}

	@Override
	public String toString(int level) {
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitDecVar(this, obj);
	}
	
	
}
