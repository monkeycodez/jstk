package org.jstk.parse;

import org.jstk.jlang.LObj;
import org.jstk.jlang.Obj;

public class NumExpr implements Expr{

	private final LObj num;

	public NumExpr(String s) {
		num = new LObj(Long.parseLong(s));
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		return num;
	}
}
