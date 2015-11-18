package interfaces;

import classes.ArithmeticExpression;
import classes.Assignment;
import classes.Break;
import classes.Conditional;
import classes.Continue;
import classes.DecVar;
import classes.Declarations;
import classes.Expression;
import classes.Factor;
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
import classes.Statement;
import classes.Term;
import classes.Value;
import classes.While;

public interface Visitor {

	public Object visitProgram(Program prog, Object arg);
	public Object visitExpression(Expression exp, Object arg);
	public Object visitArithmeticExpression(ArithmeticExpression aE, Object arg);
	public Object visitAssignment(Assignment asg, Object arg);
	public Object visitFunction(Function func, Object arg);
	public Object visitFunctionDeclaration(FunctionDeclaration funcDec, Object arg);
	public Object visitFunctionCalling(FunctionCalling funcCalling, Object arg);
	public Object visitParamDeclaration(ParamDeclaration paramDec, Object arg);
	public Object visitConditional(Conditional cond, Object arg);
	public Object visitWhile(While whi, Object arg);
	public Object visitReturn(Return ret, Object arg);
	public Object visitBreak(Break brek, Object arg);
	public Object visitDeclarations(Declarations decs, Object arg);
	public Object visitContinue(Continue cont, Object arg);
	public Object visitDecVar(DecVar decVar, Object arg);
	public Object visitValue(Value value, Object arg);
	public Object visitTerm(Term term, Object arg);
	public Object visitFactorValue(FactorValue fc, Object arg);
	public Object visitFactorExpression(FactorExpression fe, Object arg);
	public Object visitFactorID(FactorID fid, Object arg);
	public Object visitFactorFunctionCalling(FactorFunctionCalling ffc, Object arg);
//	public Object visitFactor(Factor f, Object arg);
//	public Object visitStatement(Statement st, Object arg);

}
