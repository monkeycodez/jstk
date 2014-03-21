package org.jstk.parse;

import org.jstk.jlang.Obj;

public interface Expr{

	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str);
	
}
