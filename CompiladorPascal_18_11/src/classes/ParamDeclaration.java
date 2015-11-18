package classes;

import interfaces.Visitor;

import java.util.List;

import util.AST.AST;

public class ParamDeclaration extends AST {
	
	private List<DecVar> decVars;
	
	public ParamDeclaration(List<DecVar> decVars) {
		this.decVars = decVars;
	}

	@Override
	public String toString(int level) {
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitParamDeclaration(this, obj);
	}

}
