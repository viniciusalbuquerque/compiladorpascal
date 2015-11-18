package classes;

import interfaces.Visitor;
import util.AST.AST;

public class Term extends AST {
	
	//TODO CRIAR LISTAS
	
	private int operation;
	private Factor factor;
	
	public Term(Factor f) {
		this.factor = f;
	}
	
	public Term(int operation, Factor f) {
		this.operation = operation;
		this.factor = f;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitTerm(this, obj);
	}


}
