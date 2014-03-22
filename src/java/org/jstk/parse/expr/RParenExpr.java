package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class RParenExpr implements Expr{

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		//NOTE: should never be called
		System.err.println("ERROR: RParenExpr.eval called");
		return null;
	}

}
