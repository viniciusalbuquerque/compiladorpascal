package classes;

import interfaces.Visitor;

import java.util.ArrayList;

import util.AST.AST;

public class Declarations extends AST {
	
	private ArrayList<DecVar> decVars;
	
	public Declarations(ArrayList<DecVar> decVars) {
		this.decVars = decVars;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitDeclarations(this, obj);
	}

}
