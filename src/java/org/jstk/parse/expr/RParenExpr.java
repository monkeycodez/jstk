package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public final class RParenExpr implements Expr{

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		//ERROR: this should never be called
		return null;
	}

	@Override
	public int lineno(){
		//ERROR: this should never be called
		return 0;
	}

}
