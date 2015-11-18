package classes;

import interfaces.Visitor;
import util.AST.AST;

public class FunctionDeclaration extends AST {
	
	private String id;
	private ParamDeclaration pD;
	private int type;
	
	public FunctionDeclaration(String id, ParamDeclaration pD, int type) {
		this.id = id;
		this.pD = pD;
		this.type = type;
	}

	@Override
	public String toString(int level) {
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFunctionDeclaration(this, obj);
	}

}
