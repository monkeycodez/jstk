package org.jstk.parse.expr;

import org.jstk.jlang.LObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;

public class NumExpr implements Expr{

	private final LObj num;

	public NumExpr(String s) {
		num = new LObj(Long.parseLong(s));
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return num;
	}
}
