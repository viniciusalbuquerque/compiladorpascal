package checker;

import util.symbolsTable.IdentificationTable;
import classes.ArithmeticExpression;
import classes.Assignment;
import classes.Break;
import classes.Conditional;
import classes.Continue;
import classes.DecVar;
import classes.Declarations;
import classes.Expression;
import classes.FactorExpression;
import classes.FactorFunctionCalling;
import classes.FactorID;
import classes.FactorValue;
import classes.Function;
import classes.FunctionCalling;
import classes.FunctionDeclaration;
import classes.ParamDeclaration;
import classes.Program;
import classes.Return;
import classes.Term;
import classes.Value;
import classes.While;
import interfaces.Visitor;

public class Checker implements Visitor {
	
	private IdentificationTable mIdTable;
	
	public Checker(Program p) {
		check(p);
	}
	
	private void check(Program prog) {
		mIdTable = new IdentificationTable();
		prog.visit(this, null);
	}

	@Override
	public Object visitProgram(Program prog, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitExpression(Expression exp, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitArithmeticExpression(ArithmeticExpression aE, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitAssignment(Assignment asg, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFunction(Function func, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFunctionDeclaration(FunctionDeclaration funcDec,
			Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFunctionCalling(FunctionCalling funcCalling, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitParamDeclaration(ParamDeclaration paramDec, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitConditional(Conditional cond, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitWhile(While whi, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitReturn(Return ret, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitBreak(Break brek, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitDeclarations(Declarations decs, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitContinue(Continue cont, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitDecVar(DecVar decVar, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitValue(Value value, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitTerm(Term term, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorValue(FactorValue fc, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorExpression(FactorExpression fe, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorID(FactorID fid, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorFunctionCalling(FactorFunctionCalling ffc,
			Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

}
