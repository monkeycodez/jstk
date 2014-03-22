package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public class RBraceExpr implements Expr{

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		// Pop the stack
		return stk.pop();
	}

}
