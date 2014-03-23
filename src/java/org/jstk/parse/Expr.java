package org.jstk.parse;

import org.jstk.jlang.Obj;

public interface Expr{

	Obj eval(ObjStack stk, ExeEnv env);
	
	int lineno();
	
}
