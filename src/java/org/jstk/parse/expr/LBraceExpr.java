package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class LBraceExpr implements Expr{

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		env.push_half_fram();
		return stk.pop();
	}

}
