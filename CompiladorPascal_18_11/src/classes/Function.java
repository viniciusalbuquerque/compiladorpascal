package classes;

import interfaces.Visitor;

import java.util.List;

import util.AST.AST;

public class Function extends AST {
	
	private FunctionDeclaration declaration;
	private List<Statement> mListStatement;
	
	public Function(FunctionDeclaration declaration, List<Statement> statements) {
		this.declaration = declaration;
		mListStatement = statements;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitFunction(this, obj);
	}

}
