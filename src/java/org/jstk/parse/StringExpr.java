package org.jstk.parse;

import org.jstk.jlang.Obj;
import org.jstk.jlang.StrObj;



public class StringExpr implements Expr{
	
	private StrObj str;
	
	public StringExpr(String s){
		str = new StrObj(s);
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		return this.str;
	}

}
