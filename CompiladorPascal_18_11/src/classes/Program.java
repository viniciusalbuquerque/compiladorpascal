package classes;

import interfaces.Visitor;

import java.util.List;

import util.AST.AST;

public class Program extends AST{
	
	private List<Function> mFunctions;
	private Declarations declararions;
	
	public Program(List<Function> functions, Declarations decs) {
		mFunctions = functions;
		this.declararions = decs;
	}

	@Override
	public String toString(int level) {
		return "program";
	}

	@Override
	public Object visit(Visitor v, Object obj) {
		return v.visitProgram(this, obj);
	}

}
